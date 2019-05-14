package cn.hergua.servicemodule.domain.vo;

import cn.hergua.servicemodule.domain.Auction;
import cn.hergua.servicemodule.domain.GoodsDescPicUrl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class GoodsAuctionVo {


    private String productCover;
    private List<GoodsDescPicUrl> productImages;
    private String productName;
    private String productIntroduction;
    private String productType;
    private int productQuantity;
    private BigDecimal startingPrice;
    private BigDecimal currentPrice;
    private BigDecimal dealPrice;
    private BigDecimal priceIncrease;
    private int bidderNumber;
    private int auctionTimes;
    private Timestamp shelfDate;
    private Timestamp deadline;

    public GoodsAuctionVo(Auction auction){
        productCover = auction.getGoods().getGoodsHeadPictureUrl();
        productImages = auction.getGoods().getUrls();
        productName = auction.getGoods().getGoodsName();
        productIntroduction = auction.getGoods().getIntroduce();
        productType = auction.getGoods().getType();
        productQuantity = auction.getGoods().getGoodsMount();
        startingPrice = auction.getStartingPrice();
        currentPrice = auction.getCurrentPrice();
        priceIncrease = auction.getPriceIncrease();
        deadline = auction.getTransactionTime();
    }


    public String getProductCover() {
        return productCover;
    }

    public void setProductCover(String productCover) {
        this.productCover = productCover;
    }

    public List<GoodsDescPicUrl> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<GoodsDescPicUrl> productImages) {
        this.productImages = productImages;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIntroduction() {
        return productIntroduction;
    }

    public void setProductIntroduction(String productIntroduction) {
        this.productIntroduction = productIntroduction;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(BigDecimal dealPrice) {
        this.dealPrice = dealPrice;
    }

    public BigDecimal getPriceIncrease() {
        return priceIncrease;
    }

    public void setPriceIncrease(BigDecimal priceIncrease) {
        this.priceIncrease = priceIncrease;
    }

    public int getBidderNumber() {
        return bidderNumber;
    }

    public void setBidderNumber(int bidderNumber) {
        this.bidderNumber = bidderNumber;
    }

    public int getAuctionTimes() {
        return auctionTimes;
    }

    public void setAuctionTimes(int auctionTimes) {
        this.auctionTimes = auctionTimes;
    }

    public Timestamp getShelfDate() {
        return shelfDate;
    }

    public void setShelfDate(Timestamp shelfDate) {
        this.shelfDate = shelfDate;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
}
