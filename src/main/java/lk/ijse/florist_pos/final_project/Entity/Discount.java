package lk.ijse.florist_pos.final_project.Entity;

public class Discount {
    private int discountId;
    private String discountType;
    private String discountDuration;
    private int discountAmount;

    public Discount() {
    }

    public Discount(int discountId, String discountType, String discountDuration, int discountAmount) {
        this.discountId = discountId;
        this.discountType = discountType;
        this.discountDuration = discountDuration;
        this.discountAmount = discountAmount;
    }
    public int getDiscountId() {
        return discountId;
    }
    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDiscountType() {
        return discountType;
    }
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountDuration() {
        return discountDuration;
    }
    public void setDiscountDuration(String discountDuration) {
        this.discountDuration = discountDuration;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return Discount.class.getSimpleName() + " [discountId=" + discountId + ", discountType=" + discountType
                + ", discountDuration=" + discountDuration + ", discountAmount=" + discountAmount + "]";

    }

}

