package com.health.demo.intelligentfitness.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/1.
 */

public class HomeFragmentListBean {

    /**
     * code : 1000
     * data : [{"content":"http://mp.weixin.qq.com/s?__biz=MjM5Njg3OTU2MQ==&mid=509677681&idx=1&sn=54b80ea6d1a948af254e1b8ce65e3e8b&scene=19#wechat_redirect","cover":"http://mmbiz.qpic.cn/mmbiz_jpg/dbvqnhWrC2EsUibPJ90ArP7Xe9a0hlHQDrV6cPibuW9vO5el5tDXNicNABMBibPK6XDeX7z5vicowUicicY05V4ao7bVQ/0?wx_fmt=jpeg","describe":"有句古话说的好，\u201c三分练，七分吃\u201d，说明吃在健身中的地位是非常重要的，很多人之所以苦练却收不到好的成效，可能就是在饮食上没有进行合理的安排，今天就给大家分享一些健身的食谱。","fcreate_time":"2017-04-28 17:21:01","id":1,"title":"三分练七分吃！健身爱好者的增肌食谱and减脂食谱，收藏起来吧！"},{"content":"http://mp.weixin.qq.com/s?__biz=MjM5Njg3OTU2MQ==&mid=509677681&idx=2&sn=d6a28f2bd470fb08905125d081b67cb7&scene=19#wechat_redirect","cover":"http://mmbiz.qpic.cn/mmbiz_jpg/dbvqnhWrC2EsUibPJ90ArP7Xe9a0hlHQDu7Fh8uGXmsJkQISANKOIj9KOm18BDshpows9ibfgprQ2IUtCVzCKUGg/0?wx_fmt=jpeg","describe":"增肌食谱主要组成主食谷类（主要补充碳水化合物）谷类（小麦、大米、黑米、小米、玉米、燕麦、荞麦等）；","fcreate_time":"2017-04-28 17:24:52","id":2,"title":"难得的良心健身食谱！超详细！"},{"content":"http://mp.weixin.qq.com/s?__biz=MjM5Njg3OTU2MQ==&mid=509677681&idx=3&sn=29c18edee1cb907661e7bbc79b747c92&scene=19#wechat_redirect","cover":"http://mmbiz.qpic.cn/mmbiz_jpg/dbvqnhWrC2EsUibPJ90ArP7Xe9a0hlHQD1KWrd2ibW7YfRM6WarvIMEjzw9fRByfm9UaUF3q5yuMTOZFMK5cAdlA/0?wx_fmt=jpeg","describe":"健身后小食\u201d不是一般的零食。你得在这份小食当中获得足够的营养，既要保护刚刚狠命运动过的肌肉，又不能喝太多蛋白粉饮料，免得热量摄入太高让辛苦的努力白费。","fcreate_time":"2017-04-28 17:26:42","id":3,"title":"健身后小食，6位全美最火教练教你怎么吃"},{"content":"http://mp.weixin.qq.com/s?__biz=MjM5Njg3OTU2MQ==&mid=509677681&idx=4&sn=0840654959cc0596bf09de11e7806ba9&scene=19#wechat_redirect","cover":"http://mmbiz.qpic.cn/mmbiz_jpg/dbvqnhWrC2EsUibPJ90ArP7Xe9a0hlHQD1JTMcCb9iaW5sF3KibUSoQhlZgUkAibVuM1LzhIdr5GFmmxWsPsibzDWbw/0?wx_fmt=jpeg","describe":"三分练，七分吃，很多健身人士在减肥期间，都有过什么能吃，什么不能吃，应该吃什么的困惑，在减肥期间选对食物，可以让我们的成果事半功倍。","fcreate_time":"2017-04-28 17:27:27","id":4,"title":"减脂食物推荐"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : http://mp.weixin.qq.com/s?__biz=MjM5Njg3OTU2MQ==&mid=509677681&idx=1&sn=54b80ea6d1a948af254e1b8ce65e3e8b&scene=19#wechat_redirect
         * cover : http://mmbiz.qpic.cn/mmbiz_jpg/dbvqnhWrC2EsUibPJ90ArP7Xe9a0hlHQDrV6cPibuW9vO5el5tDXNicNABMBibPK6XDeX7z5vicowUicicY05V4ao7bVQ/0?wx_fmt=jpeg
         * describe : 有句古话说的好，“三分练，七分吃”，说明吃在健身中的地位是非常重要的，很多人之所以苦练却收不到好的成效，可能就是在饮食上没有进行合理的安排，今天就给大家分享一些健身的食谱。
         * fcreate_time : 2017-04-28 17:21:01
         * id : 1
         * title : 三分练七分吃！健身爱好者的增肌食谱and减脂食谱，收藏起来吧！
         */

        private String content;
        private String cover;
        private String describe;
        private String fcreate_time;
        private int id;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getFcreate_time() {
            return fcreate_time;
        }

        public void setFcreate_time(String fcreate_time) {
            this.fcreate_time = fcreate_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
