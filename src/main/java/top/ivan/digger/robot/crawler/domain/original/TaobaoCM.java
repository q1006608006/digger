package top.ivan.digger.robot.crawler.domain.original;

import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/8
 */
public class TaobaoCM {

    /**
     * raw_title : Python可以这样学 董付国 9787302456469 清华大学
     * view_fee : 10.00
     * item_loc : 北京
     * comment_count :
     * detail_url : //detail.tmall.com/item.htm?id=557940505524&ns=1&abbucket=0
     * p4pTags : []
     * nid : 557940505524
     * icon : [{"icon_key":"icon-service-tianmao","show_type":"0","trace":"srpservice","icon_category":"baobei","outer_text":"0","innerText":"天猫宝贝","html":"","position":"1","title":"尚天猫，就购了","dom_class":"icon-service-tianmao","traceIdx":1,"url":"//www.tmall.com/"}]
     * comment_url : //detail.tmall.com/item.htm?id=557940505524&ns=1&abbucket=0&on_comment=1
     * pid :
     * title : <span class=H>Python</span>可以这样学 董付国 9787302456469 清华大学
     * view_price : 46.90
     * nick : 恺润图书专营店
     * view_sales : 0人付款
     * user_id : 2654111727
     * i2iTags : {"samestyle":{"url":""},"similar":{"url":""}}
     * shopcard : {"isTmall":true,"delivery":[484,1,1102],"sellerCredit":9,"totalRate":10000,"service":[484,0,0],"description":[488,0,0],"levelClasses":[{"levelClass":"icon-supple-level-zuan"},{"levelClass":"icon-supple-level-zuan"},{"levelClass":"icon-supple-level-zuan"},{"levelClass":"icon-supple-level-zuan"}],"encryptedUserId":"UvCxbMmHYvFcyMWTT"}
     * shopLink : //store.taobao.com/shop/view_shop.htm?user_number_id=2654111727
     * risk :
     * category : 50512007
     * pic_url : //g-search2.alicdn.com/img/bao/uploaded/i4/i4/2654111727/TB1gZPJa3sSMeJjSspcXXXjFXXa_!!0-item_pic.jpg
     */

    private String raw_title;
    private String view_fee;
    private String item_loc;
    private String comment_count;
    private String detail_url;
    private String nid;
    private String comment_url;
    private String pid;
    private String title;
    private String view_price;
    private String nick;
    private String view_sales;
    private String user_id;
    private I2iTagsBean i2iTags;
    private ShopcardBean shopcard;
    private String shopLink;
    private String risk;
    private String category;
    private String pic_url;
    private List<?> p4pTags;
    private List<IconBean> icon;

    public String getRaw_title() {
        return raw_title;
    }

    public void setRaw_title(String raw_title) {
        this.raw_title = raw_title;
    }

    public String getView_fee() {
        return view_fee;
    }

    public void setView_fee(String view_fee) {
        this.view_fee = view_fee;
    }

    public String getItem_loc() {
        return item_loc;
    }

    public void setItem_loc(String item_loc) {
        this.item_loc = item_loc;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getComment_url() {
        return comment_url;
    }

    public void setComment_url(String comment_url) {
        this.comment_url = comment_url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getView_price() {
        return view_price;
    }

    public void setView_price(String view_price) {
        this.view_price = view_price;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getView_sales() {
        return view_sales;
    }

    public void setView_sales(String view_sales) {
        this.view_sales = view_sales;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public I2iTagsBean getI2iTags() {
        return i2iTags;
    }

    public void setI2iTags(I2iTagsBean i2iTags) {
        this.i2iTags = i2iTags;
    }

    public ShopcardBean getShopcard() {
        return shopcard;
    }

    public void setShopcard(ShopcardBean shopcard) {
        this.shopcard = shopcard;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public List<?> getP4pTags() {
        return p4pTags;
    }

    public void setP4pTags(List<?> p4pTags) {
        this.p4pTags = p4pTags;
    }

    public List<IconBean> getIcon() {
        return icon;
    }

    public void setIcon(List<IconBean> icon) {
        this.icon = icon;
    }

    public static class I2iTagsBean {
        /**
         * samestyle : {"url":""}
         * similar : {"url":""}
         */

        private SamestyleBean samestyle;
        private SimilarBean similar;

        public SamestyleBean getSamestyle() {
            return samestyle;
        }

        public void setSamestyle(SamestyleBean samestyle) {
            this.samestyle = samestyle;
        }

        public SimilarBean getSimilar() {
            return similar;
        }

        public void setSimilar(SimilarBean similar) {
            this.similar = similar;
        }

        public static class SamestyleBean {
            /**
             * url :
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class SimilarBean {
            /**
             * url :
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class ShopcardBean {
        /**
         * isTmall : true
         * delivery : [484,1,1102]
         * sellerCredit : 9
         * totalRate : 10000
         * service : [484,0,0]
         * description : [488,0,0]
         * levelClasses : [{"levelClass":"icon-supple-level-zuan"},{"levelClass":"icon-supple-level-zuan"},{"levelClass":"icon-supple-level-zuan"},{"levelClass":"icon-supple-level-zuan"}]
         * encryptedUserId : UvCxbMmHYvFcyMWTT
         */

        private boolean isTmall;
        private int sellerCredit;
        private int totalRate;
        private String encryptedUserId;
        private List<Integer> delivery;
        private List<Integer> service;
        private List<Integer> description;
        private List<LevelClassesBean> levelClasses;

        public boolean isIsTmall() {
            return isTmall;
        }

        public void setIsTmall(boolean isTmall) {
            this.isTmall = isTmall;
        }

        public int getSellerCredit() {
            return sellerCredit;
        }

        public void setSellerCredit(int sellerCredit) {
            this.sellerCredit = sellerCredit;
        }

        public int getTotalRate() {
            return totalRate;
        }

        public void setTotalRate(int totalRate) {
            this.totalRate = totalRate;
        }

        public String getEncryptedUserId() {
            return encryptedUserId;
        }

        public void setEncryptedUserId(String encryptedUserId) {
            this.encryptedUserId = encryptedUserId;
        }

        public List<Integer> getDelivery() {
            return delivery;
        }

        public void setDelivery(List<Integer> delivery) {
            this.delivery = delivery;
        }

        public List<Integer> getService() {
            return service;
        }

        public void setService(List<Integer> service) {
            this.service = service;
        }

        public List<Integer> getDescription() {
            return description;
        }

        public void setDescription(List<Integer> description) {
            this.description = description;
        }

        public List<LevelClassesBean> getLevelClasses() {
            return levelClasses;
        }

        public void setLevelClasses(List<LevelClassesBean> levelClasses) {
            this.levelClasses = levelClasses;
        }

        public static class LevelClassesBean {
            /**
             * levelClass : icon-supple-level-zuan
             */

            private String levelClass;

            public String getLevelClass() {
                return levelClass;
            }

            public void setLevelClass(String levelClass) {
                this.levelClass = levelClass;
            }
        }
    }

    public static class IconBean {
        /**
         * icon_key : icon-service-tianmao
         * show_type : 0
         * trace : srpservice
         * icon_category : baobei
         * outer_text : 0
         * innerText : 天猫宝贝
         * html :
         * position : 1
         * title : 尚天猫，就购了
         * dom_class : icon-service-tianmao
         * traceIdx : 1
         * url : //www.tmall.com/
         */

        private String icon_key;
        private String show_type;
        private String trace;
        private String icon_category;
        private String outer_text;
        private String innerText;
        private String html;
        private String position;
        private String title;
        private String dom_class;
        private int traceIdx;
        private String url;

        public String getIcon_key() {
            return icon_key;
        }

        public void setIcon_key(String icon_key) {
            this.icon_key = icon_key;
        }

        public String getShow_type() {
            return show_type;
        }

        public void setShow_type(String show_type) {
            this.show_type = show_type;
        }

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }

        public String getIcon_category() {
            return icon_category;
        }

        public void setIcon_category(String icon_category) {
            this.icon_category = icon_category;
        }

        public String getOuter_text() {
            return outer_text;
        }

        public void setOuter_text(String outer_text) {
            this.outer_text = outer_text;
        }

        public String getInnerText() {
            return innerText;
        }

        public void setInnerText(String innerText) {
            this.innerText = innerText;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDom_class() {
            return dom_class;
        }

        public void setDom_class(String dom_class) {
            this.dom_class = dom_class;
        }

        public int getTraceIdx() {
            return traceIdx;
        }

        public void setTraceIdx(int traceIdx) {
            this.traceIdx = traceIdx;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
