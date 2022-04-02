package Test;

/*
    SELECT A.CART_ID as CART_ID, (sum(PRICE) <MINIMUM_REQUIREMENT) as ABUSED from CART_PRODUCT
    inner join COUPONS as B on A.CART_ID = B.CART_ID
    group by A.CART_ID
    order by A.CART_ID ASC;
 */
