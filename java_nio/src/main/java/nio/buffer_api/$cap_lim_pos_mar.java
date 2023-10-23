package nio.buffer_api;

public class $cap_lim_pos_mar {
    /**
     * 1）缓冲区的capacity不能为负数，缓冲区的limit不能为负数，缓冲区的position不能为负数。
     * 2）position不能大于其limit。
     * 3）limit不能大于其capacity。
     * 4）如果定义了mark，则在将position或limit调整为小于该mark的值时，该mark被丢弃。
     * 5）如果未定义mark，那么调用reset（）方法将导致抛出InvalidMarkException异常。
     * 6）如果position大于新的limit，则position的值就是新limit的值。
     * 7）当limit和position值一样时，在指定的position写入数据时会出现异常，因为此位置是被限制的。
     */
}
