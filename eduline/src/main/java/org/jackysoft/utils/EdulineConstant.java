package org.jackysoft.utils;

public interface EdulineConstant {

    enum CommonType {
        common("大家的分享")
        ,personal("我的资源");
        private String name;
        CommonType(String name){
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

    enum StyleType {
        course("课件"),
        exercise("习题"),
        material("素材"),
        Lessonplan("教案"),
        Studycase("学案"),
        other("其他");
        private String name;
        StyleType(String name){
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
    enum FileType {
        ppt("幻灯片"),
        word("WORD文档"),
        video("视频"),
        img("图片"),
        other("其他");
        private String name;
        FileType(String name){

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

}
