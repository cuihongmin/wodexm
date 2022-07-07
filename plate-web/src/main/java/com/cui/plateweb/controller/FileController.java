package com.cui.plateweb.controller;

import com.cui.common.utils.BaseResult;
import com.cui.common.utils.DateUtil;
import com.cui.user.service.CertificatesFilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Descripttion 文件上传接口
 * @Author cuihongmin
 * @Date 2022/6/16 14:01
 */

@RestController
@RequestMapping("/file")
@Api(value = "file",tags = "文件上传接口")
public class FileController {

    @Autowired
    CertificatesFilesService certificatesFilesService;
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${project.image.upload.path}") private String uploadPath;
    @Value("${project.image.request.path}") private String requestPath;

    @ApiOperation(value = "证件图片上传")
    @PostMapping(value = "/certificatesUpload",consumes = "multipart/form-data")
    public BaseResult certificatesUpload(@RequestParam("proxyfile")MultipartFile file) {

            if (!file.isEmpty()) {
                try {
                    String path=null;// 文件路径
                    String type=null;// 文件类型
                    String fileName=file.getOriginalFilename();// 文件原名称
                    // 判断文件类型
                    type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()):null;
                    if (type != null) { // 判断文件类型是否为空
                        if ("GIF".equals(type.toUpperCase())||"PING".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                            System.out.println("文件类型不正确,请按要求重新上传");
                            return null;
                        }
                    }else {
                        return BaseResult.fail("501", "文件类型为空");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        return BaseResult.fail("500", "上传失败");
    }


    @ApiOperation(value = "证件图片上传2.0" )
    @PostMapping(value = "/picTureUpload",consumes = "multipart/form-data")
    public BaseResult picTureUpload(@RequestParam("proxyfile")MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                String path=null;// 文件路径
                String type=null;// 文件类型
                String fileName=file.getOriginalFilename();// 文件原名称
                // 判断文件类型
                type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                if (type!=null) {// 判断文件类型是否为空
                    if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase()) ||"JPEG".equals(type.toUpperCase())) {
                        // 自定义的文件名称
                        String trueFileName=String.valueOf(System.currentTimeMillis()); // System.currentTimeMillis()用于获取当前系统时间，以毫秒为单位
                        String fileType = fileName.substring(fileName.lastIndexOf("."));
                        String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);  // 格式化日期,参数(日期，日期格式)
                        // 设置存放图片文件的路径
                        fileName = trueFileName+fileType;
                        path=uploadPath+"/"+relative+"/";
                        File newFile =new File(path);
                        if(!newFile.exists()) {     // exists()测试此抽象路径名表示的文件或目录是否存在
                            newFile.mkdirs();   // mkdirs()可以建立多级文件夹， mkdir()只会建立一级的文件夹
                        }
                        // 转存文件到指定的路径
                        FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + fileName));
                        byte[] bs = new byte[1024];
                        int len;
                        while ((len = fileInputStream.read(bs)) != -1) {
                            bos.write(bs, 0, len);
                        }
                        bos.flush();
                        bos.close();
                        System.out.println("文件成功上传到指定目录下");
                        //return BaseResult.success(requestPath+"/"+relative+"/"+fileName);
                        String data = requestPath+"/"+relative+"/"+fileName;
                        return certificatesFilesService.savePicTures(data);
                    }else {
                        System.out.println("文件类型,请按要求重新上传");
                        return null;
                    }
                }else {
                    return BaseResult.fail("501", "文件类型为空");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BaseResult.fail("500", "上传失败");
    }

    @ApiOperation(value = "证件图片批量上传" )
    @PostMapping(value = "/certificatesBatchUpload",consumes = "multipart/form-data")
    public BaseResult certificatesBatchUpload(@RequestParam("file")MultipartFile [] files) {
        if (files.length>0) {
            try {
                List<String> list = new ArrayList<String>();
                for (MultipartFile file : files) {
                    String path=null;// 文件路径
                    String type=null;// 文件类型
                    String fileName=file.getOriginalFilename();// 文件原名称
                    // 判断文件类型
                    type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                    if (type!=null) {// 判断文件类型是否为空
                        if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                            // 自定义的文件名称
                            String trueFileName=String.valueOf(System.currentTimeMillis());
                            String fileType = fileName.substring(fileName.lastIndexOf("."));
                            String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);
                            // 设置存放图片文件的路径
                            fileName = trueFileName+fileType;
                            path=uploadPath+"/"+relative+"/";
                            File newFile =new File(path);
                            if(!newFile.exists()) {
                                newFile.mkdirs();
                            }
                            // 转存文件到指定的路径
                            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + fileName));
                            byte[] bs = new byte[1024];
                            int len;
                            while ((len = fileInputStream.read(bs)) != -1) {
                                bos.write(bs, 0, len);
                            }
                            bos.flush();
                            bos.close();
                            System.out.println("文件成功上传到指定目录下");
                            String data = requestPath+"/"+relative+"/"+fileName;
                            list.add(data);
                        }else {
                            System.out.println("文件类型,请按要求重新上传");
                            return null;
                        }
                    }else {
                        return BaseResult.fail("501", "文件类型为空");
                    }

                }
                return certificatesFilesService.batchSaveFiles(list);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return BaseResult.fail("500", "上传失败");
    }

    @ApiOperation(value = "图片上传" )
    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public BaseResult upload(@RequestParam("proxyfile")MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String path=null;// 文件路径
                String type=null;// 文件类型
                String fileName=file.getOriginalFilename();// 文件原名称
                // 判断文件类型
                type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                if (type!=null) {// 判断文件类型是否为空
                    if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase()) ||"JPEG".equals(type.toUpperCase())) {
                        // 自定义的文件名称
                        String trueFileName=String.valueOf(System.currentTimeMillis());
                        String fileType = fileName.substring(fileName.lastIndexOf("."));
                        String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);
                        // 设置存放图片文件的路径
                        fileName = trueFileName+fileType;
                        path=uploadPath+"/"+relative+"/";
                        File newFile =new File(path);
                        if(!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        // 转存文件到指定的路径
                        FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + fileName));
                        byte[] bs = new byte[1024];
                        int len;
                        while ((len = fileInputStream.read(bs)) != -1) {
                            bos.write(bs, 0, len);
                        }
                        bos.flush();
                        bos.close();
                        System.out.println("文件成功上传到指定目录下");
                        return BaseResult.success(requestPath+"/"+relative+"/"+fileName);
                    }else {
                        System.out.println("文件类型,请按要求重新上传");
                        return null;
                    }
                }else {
                    return BaseResult.fail("501", "文件类型为空");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BaseResult.fail("500", "上传失败");
    }

    @ApiOperation(value = "上传pdf和word等" )
    @PostMapping(value = "/pdfUpload",consumes = "multipart/form-data")
    public BaseResult pdfUpload(@RequestParam("proxyfile")MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String path=null;// 文件路径
                String type=null;// 文件类型
                String fileName=file.getOriginalFilename().replaceAll(" ", "");// 文件原名称
                String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(fileName);
                // 判断文件类型
                type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                if (type!=null) {// 判断文件类型是否为空
                    if ("PDF".equals(type.toUpperCase())||"DOCX".equals(type.toUpperCase())||"DOC".equals(type.toUpperCase())
                            ||"WPS".equals(type.toUpperCase())||"WPT".equals(type.toUpperCase())||"DOTX".equals(type.toUpperCase())
                            ||"DOCM".equals(type.toUpperCase())||"DOTM".equals(type.toUpperCase())||"DOT".equals(type.toUpperCase())
                            ||"XLS".equals(type.toUpperCase())||"XLSX".equals(type.toUpperCase())||"XLT".equals(type.toUpperCase())
                            ||"ET".equals(type.toUpperCase())||"ETT".equals(type.toUpperCase())||"XLTX".equals(type.toUpperCase())
                            ||"CSV".equals(type.toUpperCase())||"XLSB".equals(type.toUpperCase())||"XLSM".equals(type.toUpperCase())
                            ||"JPEG".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())
                            ||"JPE".equals(type.toUpperCase())||"XLTM".equals(type.toUpperCase())) {
                        // 自定义的文件名称
//	                   String trueFileName=String.valueOf(System.currentTimeMillis());
//	                   String fileType = fileName.substring(fileName.lastIndexOf("."));
                        String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);
                        // 设置存放图片文件的路径
//	                   fileName = new String(fileName.getBytes(),"UTF-8")+fileType;
                        //fileName = new String(fileName.getBytes(),"UTF-8");
                        path=uploadPath+"/"+relative+"/";
                        File newFile =new File(path);
                        if(!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        // 转存文件到指定的路径
                        FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + m.replaceAll("").trim()));
                        byte[] bs = new byte[1024];
                        int len;
                        while ((len = fileInputStream.read(bs)) != -1) {
                            bos.write(bs, 0, len);
                        }
                        bos.flush();
                        bos.close();
                        System.out.println("文件成功上传到指定目录下");
                        return BaseResult.success(requestPath+"/"+relative+"/"+m.replaceAll("").trim());
                    }else {
                        System.out.println("文件类型,请按要求重新上传");
                        return null;
                    }
                }else {
                    return BaseResult.fail("501", "文件类型为空");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BaseResult.fail("500", "上传失败");
    }

    @ApiOperation(value = "上传pdf和word等(广汇化工竞标附件上传专用  目的只是为了防止上传文件名重复)" )
    @PostMapping(value = "/AllTypeUpload",consumes = "multipart/form-data")
    public BaseResult AllTypeUpload(@RequestParam("proxyfile")MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String path=null;// 文件路径
                String type=null;// 文件类型
                String fileName=file.getOriginalFilename().replaceAll(" ", "");// 文件原名称
                String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
                Pattern p = Pattern.compile(regEx);
                // 判断文件类型
                type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                //为防止上传文件名重复，先将文件名用当前时间戳进行命名
                StringBuffer nowTime = new StringBuffer();
                nowTime.append(System.currentTimeMillis());
                String name = nowTime.toString()+"."+type;
                Matcher m = p.matcher(name);
                if (type!=null) {// 判断文件类型是否为空
                    if ("PDF".equals(type.toUpperCase())||"DOCX".equals(type.toUpperCase())||"DOC".equals(type.toUpperCase())
                            ||"WPS".equals(type.toUpperCase())||"WPT".equals(type.toUpperCase())||"DOTX".equals(type.toUpperCase())
                            ||"DOCM".equals(type.toUpperCase())||"DOTM".equals(type.toUpperCase())||"DOT".equals(type.toUpperCase())
                            ||"XLS".equals(type.toUpperCase())||"XLSX".equals(type.toUpperCase())||"XLT".equals(type.toUpperCase())
                            ||"ET".equals(type.toUpperCase())||"ETT".equals(type.toUpperCase())||"XLTX".equals(type.toUpperCase())
                            ||"CSV".equals(type.toUpperCase())||"XLSB".equals(type.toUpperCase())||"XLSM".equals(type.toUpperCase())
                            ||"JPEG".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())
                            ||"JPE".equals(type.toUpperCase())||"XLTM".equals(type.toUpperCase())) {
                        // 自定义的文件名称
//	                   String trueFileName=String.valueOf(System.currentTimeMillis());
//	                   String fileType = fileName.substring(fileName.lastIndexOf("."));
                        String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);
                        // 设置存放图片文件的路径
//	                   fileName = new String(fileName.getBytes(),"UTF-8")+fileType;
                        //fileName = new String(fileName.getBytes(),"UTF-8");
                        path=uploadPath+"/"+relative+"/";
                        File newFile =new File(path);
                        if(!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        // 转存文件到指定的路径
                        FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + m.replaceAll("").trim()));
                        byte[] bs = new byte[1024];
                        int len;
                        while ((len = fileInputStream.read(bs)) != -1) {
                            bos.write(bs, 0, len);
                        }
                        bos.flush();
                        bos.close();
                        System.out.println("文件成功上传到指定目录下");
                        return BaseResult.success(requestPath+"/"+relative+"/"+m.replaceAll("").trim());
                    }else {
                        System.out.println("文件类型,请按要求重新上传");
                        return null;
                    }
                }else {
                    return BaseResult.fail("501", "文件类型为空");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BaseResult.fail("500", "上传失败");
    }

    @ApiOperation(value = "获取图片")
    @GetMapping("/getCertificateById")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id", value = "id", required = true, dataType = "String")
    })
    public void getCertificateById(String id, HttpServletResponse response){
        HttpURLConnection conn=null;
        OutputStream os=null;
        String address=null;
        try {
            address = certificatesFilesService.getPicTureById(id).getData()+"";
            URL url = new URL(address);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte data[] = readInputStream(inStream);
            inStream.read(data);  //读数据
            inStream.close();
            response.setContentType("image/jpg"); //设置返回的文件类型
            os = response.getOutputStream();
            os.write(data);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取认证文件错误:{} {}", id, address);
        } finally {
            try {
                os.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "获取图片2.0")
    @GetMapping("/getPicTureById")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id", value = "id", required = true, dataType = "String")
    })
    public BaseResult getPicTureById(String id, HttpServletRequest request, HttpServletResponse response){

        return BaseResult.success(certificatesFilesService.getPicTureById(id).getData()+"");
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        //inStream.close();
        return outStream.toByteArray();
    }


    @ApiOperation(value = "获取图片,返回base64图片格式")
    @GetMapping("/getCertificateBase64ById")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id", value = "id", required = true, dataType = "String")
    })
    public String getCertificateBase64ById(String id,HttpServletResponse httpServletResponse){
        try {
            String data = certificatesFilesService.getFileById(id).getData()+"";
            data = "data:image/jpg;base64,"+data;
            return  data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperation(value = "下载")
    @GetMapping("/downloadFile")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "url", value = "url", required = true, dataType = "String")
    })
    public void downloadFile(String url,HttpServletResponse response){
        try {
//			http 地址专程本地地址 http://192.168.200.105/20190401/1554109897431.jpg --->/20190401/1554109897431.jpg
//			https://www.hm-w.com/pic-proxy/20200107/中标公告兰州永诚化工兰州永诚化工.png

            url = url.replace("http://", "").replace("https://", "");
            String filePath  = url.substring(url.indexOf("/"));
            if(filePath.indexOf("/pic-proxy/")!=-1){
                filePath = filePath.replace("/pic-proxy/", "");
            }
            File mFile = new File(uploadPath+filePath);
            if( !mFile.exists()){
                throw new Exception("下载路径不存在");
            }
            String [] str = filePath.split("/");
            //String fileName = URLEncoder.encode(filePath.split("/")[2], "utf-8");
            //response.setHeader("Content-Disposition", "attachment; filename="+MimeUtility.encodeWord(filePath.split("/")[2]));
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(str[str.length-1], "utf-8"));
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(mFile);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        String base64Str = "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";
        BASE64Decoder decoder =new BASE64Decoder();
        try {
            //解码过程，即将base64字符串转换成二进制流
            byte[] imageByte=decoder.decodeBuffer(base64Str);
            System.out.println("===="+imageByte);
            //生成图片路径和文件名
            // 自定义的文件名称
            String trueFileName=String.valueOf(System.currentTimeMillis());
            String fileType = ".jpg";
            String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);
            // 设置存放图片文件的路径
            String fileName = trueFileName+fileType;
            String pathString ="/data/"+"/"+relative+"/";
            //String pathString ="F://";
            File newFile =new File(pathString);
            if(!newFile.exists()) {
                newFile.mkdirs();
            }
            OutputStream out =new FileOutputStream(pathString + fileName);
            out.write(imageByte);
            out.flush();
            out.close();
            String data = "http://192.168.200.105"+"/"+relative+"/"+fileName;
            //String data = relative+"/"+fileName;
            System.out.println("data"+data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }


    }


}


