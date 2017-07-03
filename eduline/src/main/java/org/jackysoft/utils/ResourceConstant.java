package org.jackysoft.utils;

public interface ResourceConstant {

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
        public int getValue(){
            return ordinal();
        }
    }

    enum ContentStyle{
        course("课件"),
        exercise("习题"),
        material("素材"),
        Lessonplan("教案"),
        Studycase("学案"),
        other("其他");
        private String name;
        ContentStyle(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public int getValue(){
            return ordinal();
        }
    }
    enum DetailType{
        ppt("幻灯片"),
        word("WORD文档"),
        video("视频"),
        img("图片"),
        other("其他");
        private String name;
        DetailType(String name){

            this.name = name;
        }
        public String getName(){
            return name;
        }
        public int getValue(){
            return ordinal();
        }

    }

    enum HoweworkStatus{
        submited("已交"),
        unsubmit("未交");
        private String name;
        HoweworkStatus(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public int getValue(){
            return ordinal();
        }
    }

}
