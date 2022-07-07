package com.wanqiao.mogao.project.dfs.controller;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.StringUtils;
import com.wanqiao.mogao.common.utils.file.FileUploadNewUtils;
import com.wanqiao.mogao.common.utils.file.FileUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.project.dfs.config.DfsConfig;
import com.wanqiao.mogao.project.dfs.domain.SysFile;
import com.wanqiao.mogao.project.dfs.service.ISysFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 通用请求处理
 *
 * @author Mogao
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController extends BaseController {
    @Autowired
    private DfsConfig dfsConfig;

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("download")
    public void download(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = dfsConfig.getPath() + fileName;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求(多文件上传)
     */
    @PostMapping("uploadBatch")
    public AjaxResult handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");

        List<SysFile> fileList = new ArrayList<SysFile>();
        MultipartFile file = null;
        // 上传文件路径
        String filePath = dfsConfig.getPath();

        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    // 上传并返回新文件名称
                    Map<String, Object> map = FileUploadNewUtils.upload(filePath, file);
                    String url = dfsConfig.getDomain() + map.get("fileName");
                    SysFile sysFile = insertSysFile(map, url);
                    fileList.add(sysFile);
                } catch (Exception e) {
                    log.error("上传文件失败", e);
                    return AjaxResult.error("上传失败");
                }
            }
        }

        return AjaxResult.success(fileList);
    }

    /**
     * 将文件信息存入文件表
     *
     * @param map
     * @param url
     * @return
     */
    private SysFile insertSysFile(Map<String, Object> map, String url) {
        SysFile sysFile = new SysFile();
        sysFile.setUid(WanqiaoPrimaryKeyUtils.getSerialNumberString());
        sysFile.setName((String) map.get("originalFileName"));
        sysFile.setFileSuffix((String) map.get("extension"));
        sysFile.setSize((Long) map.get("fileSize"));
        sysFile.setUrl(url);
        sysFile.setCreateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId() +""));
        sysFile.setIsDel("1");
        sysFile.setBusinessId("0");
        sysFileService.insertSysFile(sysFile);
        return sysFile;
    }

    /**
     * 通用上传请求(多文件上传)
     */
    @PostMapping("uploadToFtp")
    public AjaxResult handleFileUploadToFtp(HttpServletRequest request, MultipartFile file){
        // 上传文件路径
        String ftpIp = dfsConfig.getFtpIp();
        String dataPath = DateUtils.dateTime();
        String ftpPath = dfsConfig.getFtpPath() + dataPath;
        String ftpUserName = dfsConfig.getFtpUserName();
        String ftpPassword = dfsConfig.getFtpPassword();
        String ftpPort = dfsConfig.getFtpPort();
        // 上传并返回新文件名称
        try {
            Map<String, Object> map = FileUploadNewUtils.uploadToRemote(ftpIp, ftpPath, ftpUserName, ftpPassword, ftpPort, file);
            String url = dfsConfig.getDomain() + dataPath+"/" + map.get("fileName");
            SysFile sysFile = insertSysFile(map, url);
            return AjaxResult.success(sysFile);
        }catch (Exception e){
            logger.error(String.valueOf(e));
        }
        return AjaxResult.error("上传失败!");
    }


    /**
     * 根据业务表查询附件信息
     */
    @GetMapping("listFilesByBussinessId")
    public AjaxResult listFilesByBussinessId(String businessId) {
        return AjaxResult.success(sysFileService.listFilesByBussinessId(businessId));
    }

    /**
     * 修改文件表中业务字段id
     */
    @PostMapping("updateBusinessId")
    public AjaxResult updateBusinessId(HttpServletRequest request) {
        String removedFileStr = request.getParameter("removedFileStr");
        String finishedFileStr = request.getParameter("finishedFileStr");
        String businessId = request.getParameter("businessId");
        //更新文件业务字段
        List finishedFileList = new ArrayList();
        try {
            if (StringUtils.isNotEmpty(finishedFileStr)) {
                finishedFileList = Arrays.asList(finishedFileStr.split(","));
                for (int i = 0; i < finishedFileList.size(); i++) {
                    SysFile sysFile = new SysFile();
                    sysFile.setUid((String) finishedFileList.get(i));
                    sysFile.setBusinessId(businessId);
                    sysFileService.updateSysFile(sysFile);
                }
            }
            //过滤掉修改时删掉的文件,将已经删掉了的文件isDel为0
            List removedFileList = new ArrayList();
            if (StringUtils.isNotEmpty(removedFileStr)) {
                removedFileList = Arrays.asList(removedFileStr.split(","));
                for (int i = 0; i < removedFileList.size(); i++) {
                    SysFile sysFile = new SysFile();
                    sysFile.setUid((String) removedFileList.get(i));
                    sysFile.setIsDel("0");
                    sysFileService.updateSysFile(sysFile);
                }
            }
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }

        return AjaxResult.success();
    }
}