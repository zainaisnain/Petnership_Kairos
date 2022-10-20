package com.example.petnership_kairos;

public class ActiveAdoptersData {
        private String applicantName;
        private Integer applicantImage;

        public ActiveAdoptersData(String applicantName, Integer applicantImage) {
            this.applicantName = applicantName;
            this.applicantImage = applicantImage;
        }

        public String getapplicantName() {
            return applicantName;
        }
        public Integer getapplicantImage() {
            return applicantImage;
        }

        public void setapplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public void setapplicantImage(Integer applicantImage) {
            this.applicantImage = applicantImage;
        }
    }
