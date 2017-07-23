package org.jackysoft.utils;

public interface EdulineConstant {

    enum Commontype {
        common("大家的分享")
        ,personal("我的资源");
        private String name;
        Commontype(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public String getKey(){return name();}
        public int getValue(){
            return ordinal();
        }
    }

    enum Styletype {
        course("课件"),
        exercise("习题"),
        material("素材"),
        Lessonplan("教案"),
        Studycase("学案"),
        other("其他");
        private String name;
        Styletype(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public String getKey(){return name();}
        public int getValue(){
            return ordinal();
        }
    }
    enum filetype {
        ppt("幻灯片"),
        pdf("PDF文档"),
        word("WORD文档"),
        video("视频"),
        img("图片"),
        other("其他");
        private String name;
        filetype(String name){

            this.name = name;
        }
        public String getName(){
            return name;
        }
        public String getKey(){return name();}
        public int getValue(){
            return ordinal();
        }


    }

    enum HoweworkStatus{
        submited("已交"),
        unsubmit("未交"),
        readed("已阅"),
        notreaded("未阅");

        private String name;
        HoweworkStatus(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public String getKey(){return name();}
        public int getValue(){
            return ordinal();
        }
    }

    default String getContentType(String sufix){
        String cnt = "";
        switch (sufix){
            case "pptx":
                cnt = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                break;
            case "ppt":cnt = "application/vnd.ms-powerpoint";
                break;
            case "doc":cnt="application/msword";
                break;
            case "docx":cnt="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                break;
            case "pdf":cnt="application/pdf";
                break;
            case "xls":cnt="application/vnd.ms-excel";
                break;
            case "xlsx":cnt="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                break;
            case "mp4":cnt="video/mp4";
                break;
            case "png":cnt="image/png";
                break;
            case "jpg":cnt="image/jpeg";
                break;
            case "gif":cnt="image/gif";
                break;
            default:cnt= "application/octet-stream";
        }
        return cnt;

    }
}
