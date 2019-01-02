package com.cwx.timebank.bean;

public class DiscussBean {
        private int DId;//讨论的id;
        private int TID;//讨论的标签
        private int UId;//发布人的ID
        private String petName;//发布人的昵称
        private String content;//发布的话题
        private String Tcontent;//话题的标签

        public int getDId() {
            return DId;
        }

        public void setDId(int DId) {
            this.DId = DId;
        }

        public int getTID() {
            return TID;
        }

        public void setTID(int TID) {
            this.TID = TID;
        }

        public int getUId() {
            return UId;
        }

        public void setUId(int UId) {
            this.UId = UId;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTcontent() {
            return Tcontent;
        }

        public void setTcontent(String tcontent) {
            Tcontent = tcontent;
        }

        @Override
        public String toString() {
            return "DiscussBean{" +
                    "DId=" + DId +
                    ", TID=" + TID +
                    ", UId=" + UId +
                    ", petName='" + petName + '\'' +
                    ", content='" + content + '\'' +
                    ", Tcontent='" + Tcontent + '\'' +
                    '}';
        }

}
