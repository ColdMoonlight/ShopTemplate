package com.atguigu.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UpImgUtils {
	
	/*	
	 *  1.0	keepCategory
     *  1、从request当中获取流信息
     *  2、新建一个临时文件,用输出流指向这个文件
     *  3、关闭流
     */
    public static String keepCategoryFile(HttpServletRequest request) throws IOException {

    	// 1、从request当中获取流信息
        InputStream fileSource = request.getInputStream();

        /*
        * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
        * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
        */
        
        String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
        String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
        
        String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
        System.out.println(tempFileName);

        //2、新建一个临时文件,用输出流指向这个文件

        //建一个文件
        File tempFile = new File( tempFileName );

        //用输出流指向这个文件

        FileOutputStream outputStream = new FileOutputStream( tempFile );

        //我们就每次读写10K,我们的文件小，这个就已经够用了
        byte[] b = new byte[1024*10];

        int n = 0 ;

        //读写文件,-1标识为空
        while( (n = fileSource.read(b) ) != -1 ) {
            outputStream.write(b, 0, n);
        }

        //3、关闭流
        fileSource.close();
        outputStream.close();

        //第二部分......................................................
        /**
        * 思路
        * 1、获取文件的名称，并解决中文乱码
        * 2、获取文件的内容
        * 3、保存文件
        */

        //第二部分 1、获取文件的名称，并解决中文乱码

        RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
        RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
        Long len = rf.length();
        String line;
        String lineEnd="";
        long nextend =  len - 1;  //指针是从0到length-1
        rf.seek(nextend); //seek到最后一个字符
        int c = -1,num=3,k=0;
        while (nextend >= 0) {
          c = rf.read();
          if (c == '\n'){
        	  System.out.println(k);
        	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
	        //指针指到了这一行的开头字符
            line = rf.readLine();
            //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
            System.out.println(line);
            k++;
            if(k==4){
            	lineEnd = line;
            	break;
            }
            //nextend--;
          }
          rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                           //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
          if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
            System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
          }
          nextend--;
      }
        
        System.out.println("lineEnd"+lineEnd);
        randomFile.readLine();//先读取一行
        String str = randomFile.readLine();//读取第二行
        int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
        int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
       

        String filename = str.substring(beginIndex, endIndex);

        //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
        endIndex = filename.lastIndexOf("\\") + 1;
        if( endIndex > -1 ) {
            filename = filename.substring(endIndex);
        }
        //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

        //解决上传文件中文名字乱码
        filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("filename: " + filename );

        //第二部分 2、获取文件的内容
        //重新定位文件指针到文件头
        randomFile.seek(0);
        long startPosition = 0L;//正文开始的位置
        int i = 1;
        while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
            if( n == '\n') {
                startPosition = randomFile.getFilePointer();
                i++;
            }
        }
        //
        startPosition = randomFile.getFilePointer() - 1 ;

        //获取文件内容，结束位置
        randomFile.seek(randomFile.length() );//指针定位到尾部
        long endPosition = randomFile.getFilePointer();
        int j = 1;
        while( endPosition >= 0 && j <=2 ) {
            endPosition--;
            randomFile.seek(endPosition);
            if(randomFile.readByte() == '\n' ) {
                j++;
            }
        }

        endPosition = endPosition - 1;

        //第二部分 3、保存文件
        //设置保存上传文件的路径，我们好保存到temp中
        String realPath = request.getServletContext().getRealPath("/") + "temp";
        
//        String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\category";
        
        String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/category";//linux状态下
        
        System.out.println("文件保存路径:"+toTargetPath);
        
        realPath = toTargetPath;
        File fileupload = new File( realPath );
        File saveFile = new File(realPath,filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
        //从临时文件当中读取文件内容（根据起止位置获取）
        randomFile.seek(startPosition);
        while(startPosition < endPosition ) {
            randomAccessFile.write(randomFile.readByte());
            startPosition = randomFile.getFilePointer();
        }
        //关闭输入输出流、删除临时文件
        randomAccessFile.close();
        randomFile.close();
        String reaUrl = filename;
        String returnurl = reaUrl+"%"+lineEnd;
        return returnurl;
        }
    
    
    /*	
	 *  2.0	keepProductFile
     *  1、从request当中获取流信息
     *  2、新建一个临时文件,用输出流指向这个文件
     *  3、关闭流
     */
     public static String keepProductFile(HttpServletRequest request) throws IOException {

     	// 1、从request当中获取流信息
         InputStream fileSource = request.getInputStream();

         /*
         * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
         * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
         */
         
         String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
         String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
         
         String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
         System.out.println(tempFileName);

         //2、新建一个临时文件,用输出流指向这个文件

         //建一个文件
         File tempFile = new File( tempFileName );

         //用输出流指向这个文件

         FileOutputStream outputStream = new FileOutputStream( tempFile );

         //我们就每次读写10K,我们的文件小，这个就已经够用了
         byte[] b = new byte[1024*10];

         int n = 0 ;

         //读写文件,-1标识为空
         while( (n = fileSource.read(b) ) != -1 ) {
             outputStream.write(b, 0, n);
         }

         //3、关闭流
         fileSource.close();
         outputStream.close();

         //第二部分......................................................
         /**
         * 思路
         * 1、获取文件的名称，并解决中文乱码
         * 2、获取文件的内容
         * 3、保存文件
         */

         //第二部分 1、获取文件的名称，并解决中文乱码

         RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
         RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
         Long len = rf.length();
         String line;
         String lineEnd="";
         long nextend =  len - 1;  //指针是从0到length-1
         rf.seek(nextend); //seek到最后一个字符
         int c = -1,num=3,k=0;
         while (nextend >= 0) {
           c = rf.read();
           if (c == '\n'){
         	  System.out.println(k);
         	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
 	        //指针指到了这一行的开头字符
             line = rf.readLine();
             //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
             System.out.println(line);
             k++;
             if(k==4){
             	lineEnd = line;
             	break;
             }
//             else if(k==8){
//            	 
//             }
             //nextend--;
           }
           rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                            //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
           if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
             System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
           }
           nextend--;
       }
         
         System.out.println("lineEnd"+lineEnd);
         randomFile.readLine();//先读取一行
         String str = randomFile.readLine();//读取第二行
         int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
         int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
        

         String filename = str.substring(beginIndex, endIndex);

         //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
         endIndex = filename.lastIndexOf("\\") + 1;
         if( endIndex > -1 ) {
             filename = filename.substring(endIndex);
         }
         //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

         //解决上传文件中文名字乱码
         filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

         System.out.println("filename: " + filename );

         //第二部分 2、获取文件的内容
         //重新定位文件指针到文件头
         randomFile.seek(0);
         long startPosition = 0L;//正文开始的位置
         int i = 1;
         while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
             if( n == '\n') {
                 startPosition = randomFile.getFilePointer();
                 i++;
             }
         }
         //
         startPosition = randomFile.getFilePointer() - 1 ;

         //获取文件内容，结束位置
         randomFile.seek(randomFile.length() );//指针定位到尾部
         long endPosition = randomFile.getFilePointer();
         int j = 1;
         while( endPosition >= 0 && j <=2 ) {
             endPosition--;
             randomFile.seek(endPosition);
             if(randomFile.readByte() == '\n' ) {
                 j++;
             }
         }

         endPosition = endPosition - 1;

         //第二部分 3、保存文件
         //设置保存上传文件的路径，我们好保存到temp中
         String realPath = request.getServletContext().getRealPath("/") + "temp";
         
//         String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\product";
         
         String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/product";//linux状态下
         
         System.out.println("文件保存路径:"+toTargetPath);
         
         realPath = toTargetPath;
         File fileupload = new File( realPath );
         File saveFile = new File(realPath,filename);
         RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
         //从临时文件当中读取文件内容（根据起止位置获取）
         randomFile.seek(startPosition);
         while(startPosition < endPosition ) {
             randomAccessFile.write(randomFile.readByte());
             startPosition = randomFile.getFilePointer();
         }
         //关闭输入输出流、删除临时文件
         randomAccessFile.close();
         randomFile.close();
         String reaUrl = filename;
         String returnurl = reaUrl+"%"+lineEnd;
         return returnurl;
         }
     
     
     /*	
 	  *  3.0	keepProductImgAllFile
      *  1、从request当中获取流信息
      *  2、新建一个临时文件,用输出流指向这个文件
      *  3、关闭流
      */
      public static String keepProductImgAllFile(HttpServletRequest request) throws IOException {

      	// 1、从request当中获取流信息
          InputStream fileSource = request.getInputStream();

          /*
          * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
          * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
          */
          
          String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
          String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
          
          String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
          System.out.println(tempFileName);

          //2、新建一个临时文件,用输出流指向这个文件

          //建一个文件
          File tempFile = new File( tempFileName );

          //用输出流指向这个文件

          FileOutputStream outputStream = new FileOutputStream( tempFile );

          //我们就每次读写10K,我们的文件小，这个就已经够用了
          byte[] b = new byte[1024*10];

          int n = 0 ;

          //读写文件,-1标识为空
          while( (n = fileSource.read(b) ) != -1 ) {
              outputStream.write(b, 0, n);
          }

          //3、关闭流
          fileSource.close();
          outputStream.close();

          //第二部分......................................................
          /**
          * 思路
          * 1、获取文件的名称，并解决中文乱码
          * 2、获取文件的内容
          * 3、保存文件
          */

          //第二部分 1、获取文件的名称，并解决中文乱码

          RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
          RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
          Long len = rf.length();
          String line;
          String productIdstr="";
          String productimgSortIdstr="";
          long nextend =  len - 1;  //指针是从0到length-1
          rf.seek(nextend); //seek到最后一个字符
          int c = -1,num=3,k=0;
          while (nextend >= 0) {
            c = rf.read();
            if (c == '\n'){
          	  System.out.println(k);
          	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
  	        //指针指到了这一行的开头字符
              line = rf.readLine();
              //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
              System.out.println(line);
              k++;
              if(k==4){
            	  productimgSortIdstr =line;
              	continue;
              } else if(k==8){
            	  productIdstr = line;
            	  break;
              }
              //nextend--;
            }
            rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                             //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
            if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
              System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
            }
            nextend--;
        }
          
          System.out.println("lineEnd"+productIdstr);
          randomFile.readLine();//先读取一行
          String str = randomFile.readLine();//读取第二行
          int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
          int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
         

          String filename = str.substring(beginIndex, endIndex);

          //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
          endIndex = filename.lastIndexOf("\\") + 1;
          if( endIndex > -1 ) {
              filename = filename.substring(endIndex);
          }
          //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

          //解决上传文件中文名字乱码
          filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

          System.out.println("filename: " + filename );

          //第二部分 2、获取文件的内容
          //重新定位文件指针到文件头
          randomFile.seek(0);
          long startPosition = 0L;//正文开始的位置
          int i = 1;
          while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
              if( n == '\n') {
                  startPosition = randomFile.getFilePointer();
                  i++;
              }
          }
          //
          startPosition = randomFile.getFilePointer() - 1 ;

          //获取文件内容，结束位置
          randomFile.seek(randomFile.length() );//指针定位到尾部
          long endPosition = randomFile.getFilePointer();
          int j = 1;
          while( endPosition >= 0 && j <=2 ) {
              endPosition--;
              randomFile.seek(endPosition);
              if(randomFile.readByte() == '\n' ) {
                  j++;
              }
          }

          endPosition = endPosition - 1;

          //第二部分 3、保存文件
          //设置保存上传文件的路径，我们好保存到temp中
          String realPath = request.getServletContext().getRealPath("/") + "temp";
          
//          String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\productAll";
          
          String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/productAll";//linux状态下
          
          System.out.println("文件保存路径:"+toTargetPath);
          
          realPath = toTargetPath;
          File fileupload = new File( realPath );
          File saveFile = new File(realPath,filename);
          RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
          //从临时文件当中读取文件内容（根据起止位置获取）
          randomFile.seek(startPosition);
          while(startPosition < endPosition ) {
              randomAccessFile.write(randomFile.readByte());
              startPosition = randomFile.getFilePointer();
          }
          //关闭输入输出流、删除临时文件
          randomAccessFile.close();
          randomFile.close();
          String reaUrl = filename;
          String returnurl = reaUrl+"%"+productIdstr+"%"+productimgSortIdstr;
          return returnurl;
          }
      
      /*	
  	   *  4.0	keepShowAreaFile
       *  1、从request当中获取流信息
       *  2、新建一个临时文件,用输出流指向这个文件
       *  3、关闭流
       */
       public static String keepShowAreaFile(HttpServletRequest request) throws IOException {

       	// 1、从request当中获取流信息
           InputStream fileSource = request.getInputStream();

           /*
           * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
           * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
           */
           
           String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
           String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
           
           String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
           System.out.println(tempFileName);

           //2、新建一个临时文件,用输出流指向这个文件

           //建一个文件
           File tempFile = new File( tempFileName );

           //用输出流指向这个文件

           FileOutputStream outputStream = new FileOutputStream( tempFile );

           //我们就每次读写10K,我们的文件小，这个就已经够用了
           byte[] b = new byte[1024*10];

           int n = 0 ;

           //读写文件,-1标识为空
           while( (n = fileSource.read(b) ) != -1 ) {
               outputStream.write(b, 0, n);
           }

           //3、关闭流
           fileSource.close();
           outputStream.close();

           //第二部分......................................................
           /**
           * 思路
           * 1、获取文件的名称，并解决中文乱码
           * 2、获取文件的内容
           * 3、保存文件
           */

           //第二部分 1、获取文件的名称，并解决中文乱码

           RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
           RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
           Long len = rf.length();
           String line;
           String lineEnd="";
           long nextend =  len - 1;  //指针是从0到length-1
           rf.seek(nextend); //seek到最后一个字符
           int c = -1,num=3,k=0;
           while (nextend >= 0) {
             c = rf.read();
             if (c == '\n'){
           	  System.out.println(k);
           	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
   	        //指针指到了这一行的开头字符
               line = rf.readLine();
               //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
               System.out.println(line);
               k++;
               if(k==4){
               	lineEnd = line;
               	break;
               }
               //nextend--;
             }
             rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                              //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
             if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
               System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
             }
             nextend--;
         }
           
           System.out.println("lineEnd"+lineEnd);
           randomFile.readLine();//先读取一行
           String str = randomFile.readLine();//读取第二行
           int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
           int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
          

           String filename = str.substring(beginIndex, endIndex);

           //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
           endIndex = filename.lastIndexOf("\\") + 1;
           if( endIndex > -1 ) {
               filename = filename.substring(endIndex);
           }
           //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

           //解决上传文件中文名字乱码
           filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

           System.out.println("filename: " + filename );

           //第二部分 2、获取文件的内容
           //重新定位文件指针到文件头
           randomFile.seek(0);
           long startPosition = 0L;//正文开始的位置
           int i = 1;
           while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
               if( n == '\n') {
                   startPosition = randomFile.getFilePointer();
                   i++;
               }
           }
           //
           startPosition = randomFile.getFilePointer() - 1 ;

           //获取文件内容，结束位置
           randomFile.seek(randomFile.length() );//指针定位到尾部
           long endPosition = randomFile.getFilePointer();
           int j = 1;
           while( endPosition >= 0 && j <=2 ) {
               endPosition--;
               randomFile.seek(endPosition);
               if(randomFile.readByte() == '\n' ) {
                   j++;
               }
           }

           endPosition = endPosition - 1;

           //第二部分 3、保存文件
           //设置保存上传文件的路径，我们好保存到temp中
           String realPath = request.getServletContext().getRealPath("/") + "temp";
           
//           String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\showArea";
           
           String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/showArea";//linux状态下
           
           System.out.println("文件保存路径:"+toTargetPath);
           
           realPath = toTargetPath;
           File fileupload = new File( realPath );
           File saveFile = new File(realPath,filename);
           RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
           //从临时文件当中读取文件内容（根据起止位置获取）
           randomFile.seek(startPosition);
           while(startPosition < endPosition ) {
               randomAccessFile.write(randomFile.readByte());
               startPosition = randomFile.getFilePointer();
           }
           //关闭输入输出流、删除临时文件
           randomAccessFile.close();
           randomFile.close();
           String reaUrl = filename;
           String returnurl = reaUrl+"%"+lineEnd;
           return returnurl;
           }
       
       /*	
   	    *  5.0.1	keepActShowProWapFile
        *  1、从request当中获取流信息
        *  2、新建一个临时文件,用输出流指向这个文件
        *  3、关闭流
        */
        public static String keepActShowProWapFile(HttpServletRequest request) throws IOException {

        	// 1、从request当中获取流信息
            InputStream fileSource = request.getInputStream();

            /*
            * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
            * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
            */
            
            String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
            String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            
            String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
            System.out.println(tempFileName);

            //2、新建一个临时文件,用输出流指向这个文件

            //建一个文件
            File tempFile = new File( tempFileName );

            //用输出流指向这个文件

            FileOutputStream outputStream = new FileOutputStream( tempFile );

            //我们就每次读写10K,我们的文件小，这个就已经够用了
            byte[] b = new byte[1024*10];

            int n = 0 ;

            //读写文件,-1标识为空
            while( (n = fileSource.read(b) ) != -1 ) {
                outputStream.write(b, 0, n);
            }

            //3、关闭流
            fileSource.close();
            outputStream.close();

            //第二部分......................................................
            /**
            * 思路
            * 1、获取文件的名称，并解决中文乱码
            * 2、获取文件的内容
            * 3、保存文件
            */

            //第二部分 1、获取文件的名称，并解决中文乱码

            RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
            RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
            Long len = rf.length();
            String line;
            String lineEnd="";
            long nextend =  len - 1;  //指针是从0到length-1
            rf.seek(nextend); //seek到最后一个字符
            int c = -1,num=3,k=0;
            while (nextend >= 0) {
              c = rf.read();
              if (c == '\n'){
            	  System.out.println(k);
            	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
    	        //指针指到了这一行的开头字符
                line = rf.readLine();
                //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                System.out.println(line);
                k++;
                if(k==4){
                	lineEnd = line;
                	break;
                }
                //nextend--;
              }
              rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                               //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
              if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
              }
              nextend--;
          }
            
            System.out.println("lineEnd"+lineEnd);
            randomFile.readLine();//先读取一行
            String str = randomFile.readLine();//读取第二行
            int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
            int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
           

            String filename = str.substring(beginIndex, endIndex);

            //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
            endIndex = filename.lastIndexOf("\\") + 1;
            if( endIndex > -1 ) {
                filename = filename.substring(endIndex);
            }
            //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

            //解决上传文件中文名字乱码
            filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("filename: " + filename );

            //第二部分 2、获取文件的内容
            //重新定位文件指针到文件头
            randomFile.seek(0);
            long startPosition = 0L;//正文开始的位置
            int i = 1;
            while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                if( n == '\n') {
                    startPosition = randomFile.getFilePointer();
                    i++;
                }
            }
            //
            startPosition = randomFile.getFilePointer() - 1 ;

            //获取文件内容，结束位置
            randomFile.seek(randomFile.length() );//指针定位到尾部
            long endPosition = randomFile.getFilePointer();
            int j = 1;
            while( endPosition >= 0 && j <=2 ) {
                endPosition--;
                randomFile.seek(endPosition);
                if(randomFile.readByte() == '\n' ) {
                    j++;
                }
            }

            endPosition = endPosition - 1;

            //第二部分 3、保存文件
            //设置保存上传文件的路径，我们好保存到temp中
            String realPath = request.getServletContext().getRealPath("/") + "temp";
            
//            String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\actShowPro";
            
            String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/actShowPro";//linux状态下
            
            System.out.println("文件保存路径:"+toTargetPath);
            
            realPath = toTargetPath;
            File fileupload = new File( realPath );
            File saveFile = new File(realPath,filename);
            RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
            //从临时文件当中读取文件内容（根据起止位置获取）
            randomFile.seek(startPosition);
            while(startPosition < endPosition ) {
                randomAccessFile.write(randomFile.readByte());
                startPosition = randomFile.getFilePointer();
            }
            //关闭输入输出流、删除临时文件
            randomAccessFile.close();
            randomFile.close();
            String reaUrl = filename;
            String returnurl = reaUrl+"%"+lineEnd;
            return returnurl;
       }
        
        /*	
    	 *  5.0.2	keepActShowProPcFile
         *  1、从request当中获取流信息
         *  2、新建一个临时文件,用输出流指向这个文件
         *  3、关闭流
         */
         public static String keepActShowProPcFile(HttpServletRequest request) throws IOException {

         	// 1、从request当中获取流信息
             InputStream fileSource = request.getInputStream();

             /*
             * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
             * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
             */
             
             String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
             String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
             
             String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
             System.out.println(tempFileName);

             //2、新建一个临时文件,用输出流指向这个文件

             //建一个文件
             File tempFile = new File( tempFileName );

             //用输出流指向这个文件

             FileOutputStream outputStream = new FileOutputStream( tempFile );

             //我们就每次读写10K,我们的文件小，这个就已经够用了
             byte[] b = new byte[1024*10];

             int n = 0 ;

             //读写文件,-1标识为空
             while( (n = fileSource.read(b) ) != -1 ) {
                 outputStream.write(b, 0, n);
             }

             //3、关闭流
             fileSource.close();
             outputStream.close();

             //第二部分......................................................
             /**
             * 思路
             * 1、获取文件的名称，并解决中文乱码
             * 2、获取文件的内容
             * 3、保存文件
             */

             //第二部分 1、获取文件的名称，并解决中文乱码

             RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
             RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
             Long len = rf.length();
             String line;
             String lineEnd="";
             long nextend =  len - 1;  //指针是从0到length-1
             rf.seek(nextend); //seek到最后一个字符
             int c = -1,num=3,k=0;
             while (nextend >= 0) {
               c = rf.read();
               if (c == '\n'){
             	  System.out.println(k);
             	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
     	        //指针指到了这一行的开头字符
                 line = rf.readLine();
                 //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                 System.out.println(line);
                 k++;
                 if(k==4){
                 	lineEnd = line;
                 	break;
                 }
                 //nextend--;
               }
               rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                                //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
               if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                 System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
               }
               nextend--;
           }
             
             System.out.println("lineEnd"+lineEnd);
             randomFile.readLine();//先读取一行
             String str = randomFile.readLine();//读取第二行
             int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
             int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
            

             String filename = str.substring(beginIndex, endIndex);

             //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
             endIndex = filename.lastIndexOf("\\") + 1;
             if( endIndex > -1 ) {
                 filename = filename.substring(endIndex);
             }
             //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

             //解决上传文件中文名字乱码
             filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

             System.out.println("filename: " + filename );

             //第二部分 2、获取文件的内容
             //重新定位文件指针到文件头
             randomFile.seek(0);
             long startPosition = 0L;//正文开始的位置
             int i = 1;
             while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                 if( n == '\n') {
                     startPosition = randomFile.getFilePointer();
                     i++;
                 }
             }
             //
             startPosition = randomFile.getFilePointer() - 1 ;

             //获取文件内容，结束位置
             randomFile.seek(randomFile.length() );//指针定位到尾部
             long endPosition = randomFile.getFilePointer();
             int j = 1;
             while( endPosition >= 0 && j <=2 ) {
                 endPosition--;
                 randomFile.seek(endPosition);
                 if(randomFile.readByte() == '\n' ) {
                     j++;
                 }
             }

             endPosition = endPosition - 1;

             //第二部分 3、保存文件
             //设置保存上传文件的路径，我们好保存到temp中
             String realPath = request.getServletContext().getRealPath("/") + "temp";
             
//             String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\actShowPro";
             
             String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/actShowPro";//linux状态下
             
             System.out.println("文件保存路径:"+toTargetPath);
             
             realPath = toTargetPath;
             File fileupload = new File( realPath );
             File saveFile = new File(realPath,filename);
             RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
             //从临时文件当中读取文件内容（根据起止位置获取）
             randomFile.seek(startPosition);
             while(startPosition < endPosition ) {
                 randomAccessFile.write(randomFile.readByte());
                 startPosition = randomFile.getFilePointer();
             }
             //关闭输入输出流、删除临时文件
             randomAccessFile.close();
             randomFile.close();
             String reaUrl = filename;
             String returnurl = reaUrl+"%"+lineEnd;
             return returnurl;
        }
         
         
         /*	
     	  *  6.0.2	keepReviewAllImg
          *  1、从request当中获取流信息
          *  2、新建一个临时文件,用输出流指向这个文件
          *  3、关闭流
          */
         public static String keepReviewAllImgFile(HttpServletRequest request) throws IOException {

        	// 1、从request当中获取流信息
             InputStream fileSource = request.getInputStream();

             /*
             * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
             * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
             */
             
             String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
             String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
             
             String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
             System.out.println(tempFileName);

             //2、新建一个临时文件,用输出流指向这个文件

             //建一个文件
             File tempFile = new File( tempFileName );

             //用输出流指向这个文件

             FileOutputStream outputStream = new FileOutputStream( tempFile );

             //我们就每次读写10K,我们的文件小，这个就已经够用了
             byte[] b = new byte[1024*10];

             int n = 0 ;

             //读写文件,-1标识为空
             while( (n = fileSource.read(b) ) != -1 ) {
                 outputStream.write(b, 0, n);
             }

             //3、关闭流
             fileSource.close();
             outputStream.close();

             //第二部分......................................................
             /**
             * 思路
             * 1、获取文件的名称，并解决中文乱码
             * 2、获取文件的内容
             * 3、保存文件
             */

             //第二部分 1、获取文件的名称，并解决中文乱码

             RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
             RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
             Long len = rf.length();
             String line;
             String reviewIdstr="";
             String reviewimgSortIdstr="";
             long nextend =  len - 1;  //指针是从0到length-1
             rf.seek(nextend); //seek到最后一个字符
             int c = -1,num=3,k=0;
             while (nextend >= 0) {
               c = rf.read();
               if (c == '\n'){
             	  System.out.println(k);
             	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
     	        //指针指到了这一行的开头字符
                 line = rf.readLine();
                 //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                 System.out.println(line);
                 k++;
                 if(k==4){
                	 reviewimgSortIdstr =line;
                 	continue;
                 } else if(k==8){
                	 reviewIdstr = line;
               	  break;
                 }
                 //nextend--;
               }
               rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                                //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
               if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                 System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
               }
               nextend--;
           }
             
             System.out.println("lineEnd"+reviewIdstr);
             randomFile.readLine();//先读取一行
             String str = randomFile.readLine();//读取第二行
             int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
             int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
            

             String filename = str.substring(beginIndex, endIndex);

             //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
             endIndex = filename.lastIndexOf("\\") + 1;
             if( endIndex > -1 ) {
                 filename = filename.substring(endIndex);
             }
             //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

             //解决上传文件中文名字乱码
             filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

             System.out.println("filename: " + filename );

             //第二部分 2、获取文件的内容
             //重新定位文件指针到文件头
             randomFile.seek(0);
             long startPosition = 0L;//正文开始的位置
             int i = 1;
             while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                 if( n == '\n') {
                     startPosition = randomFile.getFilePointer();
                     i++;
                 }
             }
             //
             startPosition = randomFile.getFilePointer() - 1 ;

             //获取文件内容，结束位置
             randomFile.seek(randomFile.length() );//指针定位到尾部
             long endPosition = randomFile.getFilePointer();
             int j = 1;
             while( endPosition >= 0 && j <=2 ) {
                 endPosition--;
                 randomFile.seek(endPosition);
                 if(randomFile.readByte() == '\n' ) {
                     j++;
                 }
             }

             endPosition = endPosition - 1;

             //第二部分 3、保存文件
             //设置保存上传文件的路径，我们好保存到temp中
             String realPath = request.getServletContext().getRealPath("/") + "temp";
             
//             String toTargetPath =request.getServletContext().getRealPath("/") +"static\\img\\reviewAllImg";
             
             String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/reviewAllImg";//linux状态下
             
             System.out.println("文件保存路径:"+toTargetPath);
             
             realPath = toTargetPath;
             File fileupload = new File( realPath );
             File saveFile = new File(realPath,filename);
             RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
             //从临时文件当中读取文件内容（根据起止位置获取）
             randomFile.seek(startPosition);
             while(startPosition < endPosition ) {
                 randomAccessFile.write(randomFile.readByte());
                 startPosition = randomFile.getFilePointer();
             }
             //关闭输入输出流、删除临时文件
             randomAccessFile.close();
             randomFile.close();
             String reaUrl = filename;
             String returnurl = reaUrl+"%"+reviewIdstr+"%"+reviewimgSortIdstr;
             return returnurl;
             }

		
		/*	
   	    *  7.0.1	keepCouponWapFile
        *  1、从request当中获取流信息
        *  2、新建一个临时文件,用输出流指向这个文件
        *  3、关闭流
        */
        public static String keepCouponWapFile(HttpServletRequest request) throws IOException {

        	// 1、从request当中获取流信息
            InputStream fileSource = request.getInputStream();

            /*
            * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
            * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
            */
            
            String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
            String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            
            String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
            System.out.println(tempFileName);

            //2、新建一个临时文件,用输出流指向这个文件

            //建一个文件
            File tempFile = new File( tempFileName );

            //用输出流指向这个文件

            FileOutputStream outputStream = new FileOutputStream( tempFile );

            //我们就每次读写10K,我们的文件小，这个就已经够用了
            byte[] b = new byte[1024*10];

            int n = 0 ;

            //读写文件,-1标识为空
            while( (n = fileSource.read(b) ) != -1 ) {
                outputStream.write(b, 0, n);
            }

            //3、关闭流
            fileSource.close();
            outputStream.close();

            //第二部分......................................................
            /**
            * 思路
            * 1、获取文件的名称，并解决中文乱码
            * 2、获取文件的内容
            * 3、保存文件
            */

            //第二部分 1、获取文件的名称，并解决中文乱码

            RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
            RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
            Long len = rf.length();
            String line;
            String lineEnd="";
            long nextend =  len - 1;  //指针是从0到length-1
            rf.seek(nextend); //seek到最后一个字符
            int c = -1,num=3,k=0;
            while (nextend >= 0) {
              c = rf.read();
              if (c == '\n'){
            	  System.out.println(k);
            	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
    	        //指针指到了这一行的开头字符
                line = rf.readLine();
                //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                System.out.println(line);
                k++;
                if(k==4){
                	lineEnd = line;
                	break;
                }
                //nextend--;
              }
              rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                               //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
              if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
              }
              nextend--;
          }
            
            System.out.println("lineEnd"+lineEnd);
            randomFile.readLine();//先读取一行
            String str = randomFile.readLine();//读取第二行
            int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
            int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
           

            String filename = str.substring(beginIndex, endIndex);

            //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
            endIndex = filename.lastIndexOf("\\") + 1;
            if( endIndex > -1 ) {
                filename = filename.substring(endIndex);
            }
            //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

            //解决上传文件中文名字乱码
            filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("filename: " + filename );

            //第二部分 2、获取文件的内容
            //重新定位文件指针到文件头
            randomFile.seek(0);
            long startPosition = 0L;//正文开始的位置
            int i = 1;
            while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                if( n == '\n') {
                    startPosition = randomFile.getFilePointer();
                    i++;
                }
            }
            //
            startPosition = randomFile.getFilePointer() - 1 ;

            //获取文件内容，结束位置
            randomFile.seek(randomFile.length() );//指针定位到尾部
            long endPosition = randomFile.getFilePointer();
            int j = 1;
            while( endPosition >= 0 && j <=2 ) {
                endPosition--;
                randomFile.seek(endPosition);
                if(randomFile.readByte() == '\n' ) {
                    j++;
                }
            }

            endPosition = endPosition - 1;

            //第二部分 3、保存文件
            //设置保存上传文件的路径，我们好保存到temp中
            String realPath = request.getServletContext().getRealPath("/") + "temp";
            
            String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/Coupon";//linux状态下
            
            System.out.println("文件保存路径:"+toTargetPath);
            
            realPath = toTargetPath;
            File fileupload = new File( realPath );
            File saveFile = new File(realPath,filename);
            RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
            //从临时文件当中读取文件内容（根据起止位置获取）
            randomFile.seek(startPosition);
            while(startPosition < endPosition ) {
                randomAccessFile.write(randomFile.readByte());
                startPosition = randomFile.getFilePointer();
            }
            //关闭输入输出流、删除临时文件
            randomAccessFile.close();
            randomFile.close();
            String reaUrl = filename;
            String returnurl = reaUrl+"%"+lineEnd;
            return returnurl;
       }

	        
        /*	
    	 *  7.0.2	keepCouponPcFile
         *  1、从request当中获取流信息
         *  2、新建一个临时文件,用输出流指向这个文件
         *  3、关闭流
         */
        public static String keepCouponPcFile(HttpServletRequest request) throws IOException {

         	// 1、从request当中获取流信息
             InputStream fileSource = request.getInputStream();

             /*
             * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
             * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
             */
             
             String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
             String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
             
             String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
             System.out.println(tempFileName);

             //2、新建一个临时文件,用输出流指向这个文件

             //建一个文件
             File tempFile = new File( tempFileName );

             //用输出流指向这个文件

             FileOutputStream outputStream = new FileOutputStream( tempFile );

             //我们就每次读写10K,我们的文件小，这个就已经够用了
             byte[] b = new byte[1024*10];

             int n = 0 ;

             //读写文件,-1标识为空
             while( (n = fileSource.read(b) ) != -1 ) {
                 outputStream.write(b, 0, n);
             }

             //3、关闭流
             fileSource.close();
             outputStream.close();

             //第二部分......................................................
             /**
             * 思路
             * 1、获取文件的名称，并解决中文乱码
             * 2、获取文件的内容
             * 3、保存文件
             */

             //第二部分 1、获取文件的名称，并解决中文乱码

             RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
             RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
             Long len = rf.length();
             String line;
             String lineEnd="";
             long nextend =  len - 1;  //指针是从0到length-1
             rf.seek(nextend); //seek到最后一个字符
             int c = -1,num=3,k=0;
             while (nextend >= 0) {
               c = rf.read();
               if (c == '\n'){
             	  System.out.println(k);
             	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
     	        //指针指到了这一行的开头字符
                 line = rf.readLine();
                 //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                 System.out.println(line);
                 k++;
                 if(k==4){
                 	lineEnd = line;
                 	break;
                 }
                 //nextend--;
               }
               rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                                //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
               if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                 System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
               }
               nextend--;
           }
             
             System.out.println("lineEnd"+lineEnd);
             randomFile.readLine();//先读取一行
             String str = randomFile.readLine();//读取第二行
             int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
             int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
            

             String filename = str.substring(beginIndex, endIndex);

             //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
             endIndex = filename.lastIndexOf("\\") + 1;
             if( endIndex > -1 ) {
                 filename = filename.substring(endIndex);
             }
             //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

             //解决上传文件中文名字乱码
             filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

             System.out.println("filename: " + filename );

             //第二部分 2、获取文件的内容
             //重新定位文件指针到文件头
             randomFile.seek(0);
             long startPosition = 0L;//正文开始的位置
             int i = 1;
             while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                 if( n == '\n') {
                     startPosition = randomFile.getFilePointer();
                     i++;
                 }
             }
             //
             startPosition = randomFile.getFilePointer() - 1 ;

             //获取文件内容，结束位置
             randomFile.seek(randomFile.length() );//指针定位到尾部
             long endPosition = randomFile.getFilePointer();
             int j = 1;
             while( endPosition >= 0 && j <=2 ) {
                 endPosition--;
                 randomFile.seek(endPosition);
                 if(randomFile.readByte() == '\n' ) {
                     j++;
                 }
             }

             endPosition = endPosition - 1;

             //第二部分 3、保存文件
             //设置保存上传文件的路径，我们好保存到temp中
             String realPath = request.getServletContext().getRealPath("/") + "temp";
             
             String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/Coupon";//linux状态下
             
             System.out.println("文件保存路径:"+toTargetPath);
             
             realPath = toTargetPath;
             File fileupload = new File( realPath );
             File saveFile = new File(realPath,filename);
             RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
             //从临时文件当中读取文件内容（根据起止位置获取）
             randomFile.seek(startPosition);
             while(startPosition < endPosition ) {
                 randomAccessFile.write(randomFile.readByte());
                 startPosition = randomFile.getFilePointer();
             }
             //关闭输入输出流、删除临时文件
             randomAccessFile.close();
             randomFile.close();
             String reaUrl = filename;
             String returnurl = reaUrl+"%"+lineEnd;
             return returnurl;
        }

         /*	
     	  *  8.0.1	keepSlideWapFile
          *  1、从request当中获取流信息
          *  2、新建一个临时文件,用输出流指向这个文件
          *  3、关闭流
          */
        public static String keepSlideWapFile(HttpServletRequest request) throws IOException {

          	// 1、从request当中获取流信息
              InputStream fileSource = request.getInputStream();

              /*
              * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
              * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
              */
              
              String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
              String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
              
              String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
              System.out.println(tempFileName);

              //2、新建一个临时文件,用输出流指向这个文件

              //建一个文件
              File tempFile = new File( tempFileName );

              //用输出流指向这个文件

              FileOutputStream outputStream = new FileOutputStream( tempFile );

              //我们就每次读写10K,我们的文件小，这个就已经够用了
              byte[] b = new byte[1024*10];

              int n = 0 ;

              //读写文件,-1标识为空
              while( (n = fileSource.read(b) ) != -1 ) {
                  outputStream.write(b, 0, n);
              }

              //3、关闭流
              fileSource.close();
              outputStream.close();

              //第二部分......................................................
              /**
              * 思路
              * 1、获取文件的名称，并解决中文乱码
              * 2、获取文件的内容
              * 3、保存文件
              */

              //第二部分 1、获取文件的名称，并解决中文乱码

              RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
              RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
              Long len = rf.length();
              String line;
              String lineEnd="";
              long nextend =  len - 1;  //指针是从0到length-1
              rf.seek(nextend); //seek到最后一个字符
              int c = -1,num=3,k=0;
              while (nextend >= 0) {
                c = rf.read();
                if (c == '\n'){
              	  System.out.println(k);
              	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
      	        //指针指到了这一行的开头字符
                  line = rf.readLine();
                  //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                  System.out.println(line);
                  k++;
                  if(k==4){
                  	lineEnd = line;
                  	break;
                  }
                  //nextend--;
                }
                rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                                 //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
                if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                  System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
                }
                nextend--;
            }
              
              System.out.println("lineEnd"+lineEnd);
              randomFile.readLine();//先读取一行
              String str = randomFile.readLine();//读取第二行
              int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
              int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
             

              String filename = str.substring(beginIndex, endIndex);

              //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
              endIndex = filename.lastIndexOf("\\") + 1;
              if( endIndex > -1 ) {
                  filename = filename.substring(endIndex);
              }
              //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

              //解决上传文件中文名字乱码
              filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

              System.out.println("filename: " + filename );

              //第二部分 2、获取文件的内容
              //重新定位文件指针到文件头
              randomFile.seek(0);
              long startPosition = 0L;//正文开始的位置
              int i = 1;
              while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                  if( n == '\n') {
                      startPosition = randomFile.getFilePointer();
                      i++;
                  }
              }
              //
              startPosition = randomFile.getFilePointer() - 1 ;

              //获取文件内容，结束位置
              randomFile.seek(randomFile.length() );//指针定位到尾部
              long endPosition = randomFile.getFilePointer();
              int j = 1;
              while( endPosition >= 0 && j <=2 ) {
                  endPosition--;
                  randomFile.seek(endPosition);
                  if(randomFile.readByte() == '\n' ) {
                      j++;
                  }
              }

              endPosition = endPosition - 1;

              //第二部分 3、保存文件
              //设置保存上传文件的路径，我们好保存到temp中
              String realPath = request.getServletContext().getRealPath("/") + "temp";
              
              String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/Slide";//linux状态下
              
              System.out.println("文件保存路径:"+toTargetPath);
              
              realPath = toTargetPath;
              File fileupload = new File( realPath );
              File saveFile = new File(realPath,filename);
              RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
              //从临时文件当中读取文件内容（根据起止位置获取）
              randomFile.seek(startPosition);
              while(startPosition < endPosition ) {
                  randomAccessFile.write(randomFile.readByte());
                  startPosition = randomFile.getFilePointer();
              }
              //关闭输入输出流、删除临时文件
              randomAccessFile.close();
              randomFile.close();
              String reaUrl = filename;
              String returnurl = reaUrl+"%"+lineEnd;
              return returnurl;
         }

  	        
          /*	
      	   * 8.0.2	keepSlidePcFile
           *  1、从request当中获取流信息
           *  2、新建一个临时文件,用输出流指向这个文件
           *  3、关闭流
           */
          public static String keepSlidePcFile(HttpServletRequest request) throws IOException {

           	// 1、从request当中获取流信息
               InputStream fileSource = request.getInputStream();

               /*
               * 临时文件的存储路径(我们在webContent下新建一个temp文件夹，发布项目的时候很可能因为temp为空，
               * 没在tomcat中建立一个文件夹，到时候自己在发布的项目中添加一个即可)
               */
               
               String returnUrlll = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
               String pathll = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
               
               String tempFileName = request.getServletContext().getRealPath("/") + "temp/tempfile.txt";
               System.out.println(tempFileName);

               //2、新建一个临时文件,用输出流指向这个文件

               //建一个文件
               File tempFile = new File( tempFileName );

               //用输出流指向这个文件

               FileOutputStream outputStream = new FileOutputStream( tempFile );

               //我们就每次读写10K,我们的文件小，这个就已经够用了
               byte[] b = new byte[1024*10];

               int n = 0 ;

               //读写文件,-1标识为空
               while( (n = fileSource.read(b) ) != -1 ) {
                   outputStream.write(b, 0, n);
               }

               //3、关闭流
               fileSource.close();
               outputStream.close();

               //第二部分......................................................
               /**
               * 思路
               * 1、获取文件的名称，并解决中文乱码
               * 2、获取文件的内容
               * 3、保存文件
               */

               //第二部分 1、获取文件的名称，并解决中文乱码

               RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
               RandomAccessFile rf = new RandomAccessFile(tempFile,"r");
               Long len = rf.length();
               String line;
               String lineEnd="";
               long nextend =  len - 1;  //指针是从0到length-1
               rf.seek(nextend); //seek到最后一个字符
               int c = -1,num=3,k=0;
               while (nextend >= 0) {
                 c = rf.read();
                 if (c == '\n'){
               	  System.out.println(k);
               	//只有行与行之间才有\r\n，这表示读到每一行上一行的末尾的\n，而执行完read后，
       	        //指针指到了这一行的开头字符
                   line = rf.readLine();
                   //RandomAccessFile的readLine方法读取文本为ISO-8859-1，需要转化为windows默认编码gbk
                   System.out.println(line);
                   k++;
                   if(k==4){
                   	lineEnd = line;
                   	break;
                   }
                   //nextend--;
                 }
                 rf.seek(nextend);//这一句必须在这个位置，如果在nextend--后，那么导致进0循环后去seek-1索引，报异常
                                  //如果在read()以前，那么导致进入0循环时，因为read指针到1，第一行少读取一个字符
                 if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                   System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), "gbk"));
                 }
                 nextend--;
             }
               
               System.out.println("lineEnd"+lineEnd);
               randomFile.readLine();//先读取一行
               String str = randomFile.readLine();//读取第二行
               int beginIndex = str.lastIndexOf("filename=\"") + 10;//定位到文件名开始的地方
               int endIndex = str.lastIndexOf("\"");//定位到文件名结尾的地方
              

               String filename = str.substring(beginIndex, endIndex);

               //判断文件名是全路径名还是只是文件名（google和火狐是只是文件名，微软系列是全路径名）
               endIndex = filename.lastIndexOf("\\") + 1;
               if( endIndex > -1 ) {
                   filename = filename.substring(endIndex);
               }
               //经过上面的这几步，我们就已经获取到了文件名，我们还需要解决一下中文名乱码的问题

               //解决上传文件中文名字乱码
               filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");

               System.out.println("filename: " + filename );

               //第二部分 2、获取文件的内容
               //重新定位文件指针到文件头
               randomFile.seek(0);
               long startPosition = 0L;//正文开始的位置
               int i = 1;
               while( ( n = randomFile.readByte() ) != -1 && i <=4 ) {
                   if( n == '\n') {
                       startPosition = randomFile.getFilePointer();
                       i++;
                   }
               }
               //
               startPosition = randomFile.getFilePointer() - 1 ;

               //获取文件内容，结束位置
               randomFile.seek(randomFile.length() );//指针定位到尾部
               long endPosition = randomFile.getFilePointer();
               int j = 1;
               while( endPosition >= 0 && j <=2 ) {
                   endPosition--;
                   randomFile.seek(endPosition);
                   if(randomFile.readByte() == '\n' ) {
                       j++;
                   }
               }

               endPosition = endPosition - 1;

               //第二部分 3、保存文件
               //设置保存上传文件的路径，我们好保存到temp中
               String realPath = request.getServletContext().getRealPath("/") + "temp";
               
               String toTargetPath =request.getServletContext().getRealPath("/") +"static/img/Slide";//linux状态下
               
               System.out.println("文件保存路径:"+toTargetPath);
               
               realPath = toTargetPath;
               File fileupload = new File( realPath );
               File saveFile = new File(realPath,filename);
               RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
               //从临时文件当中读取文件内容（根据起止位置获取）
               randomFile.seek(startPosition);
               while(startPosition < endPosition ) {
                   randomAccessFile.write(randomFile.readByte());
                   startPosition = randomFile.getFilePointer();
               }
               //关闭输入输出流、删除临时文件
               randomAccessFile.close();
               randomFile.close();
               String reaUrl = filename;
               String returnurl = reaUrl+"%"+lineEnd;
               return returnurl;
          }

}
