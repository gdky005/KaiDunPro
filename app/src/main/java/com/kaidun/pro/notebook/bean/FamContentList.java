package com.kaidun.pro.notebook.bean;

import java.util.List;

/**
 * @author Yunr
 * @date 2018/01/30 12:51
 */
public class FamContentList {

    /**
     * slideCode : 1
     * FamilyContactList : [{"ccId":"63E6C5E6A6A3BD38E0530C01000A8339","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 11.Review1-2","csUrl":"","kwcmSendTime":"2018-01-29","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":5,"text":"\u2026\u2026\u2026是吧","writingRate":"0%"},{"ccId":"6267F9D72CD20203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 18.UNIT14","csUrl":"","kwcmSendTime":"2018-01-29","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":4,"text":"If you wish to proceed to the MA,be sure to inform the university of your choice","writingRate":"0%"},{"ccId":"6267F9D72CC30203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 3.UNIT3","csUrl":"","kwcmSendTime":"2018-01-27","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":4,"text":"Six months later, donations allow a bridge to be built between the two mountains and Wawa finally gets to walk to school","writingRate":"0%"},{"ccId":"6267F9D72CC20203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 2.UNIT2","csUrl":"","kwcmSendTime":"2018-01-16","limit":0,"listingRate":"0%","option":"A","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":5,"text":"WaWa with a big mouth meets many animals","writingRate":"0%"},{"ccId":"6267F9D72CD00203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 16.Review3","csUrl":"","kwcmSendTime":"2018-01-11","limit":0,"listingRate":"0%","option":"A-","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":3,"text":"When a teacher, Nie, visits Wawa's home with a pair of red rain boots for Naxiang, Wawa's secret is revealed","writingRate":"0%"}]
     */

    private int slideCode;
    private List<FamContent> FamilyContactList;

    public int getSlideCode() {
        return slideCode;
    }

    public void setSlideCode(int slideCode) {
        this.slideCode = slideCode;
    }

    public List<FamContent> getFamilyContactList() {
        return FamilyContactList;
    }

    public void setFamilyContactList(List<FamContent> familyContactList) {
        FamilyContactList = familyContactList;
    }
}
