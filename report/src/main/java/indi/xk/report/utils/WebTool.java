package indi.xk.report.utils;

/**
 * @author xk
 * 这是一个分页工具
 * 主要用于显示页码
 * pageCode要获得记录的开始索引即开始页码
 * pageNow当前页 　pageCount总页数
 * 这个工具类返回的是页索引　PageIndex
 */
public class WebTool {

    public static PageIndex getPageIndex(long pageCode, int pageNow, long pageCount) {
        long startpage = pageNow - (pageCode % 2 == 0 ? pageCode / 2 - 1 : pageCode / 2);
        long endpage = pageNow + pageCode / 2;
        if (startpage < 1) {
            startpage = 1;
            if (pageCount >= pageCode) {
                endpage = pageCode;
            } else {
                endpage = pageCount;
            }
        }
        if (endpage > pageCount) {
            endpage = pageCount;
            if ((endpage - pageCode) > 0) {
                startpage = endpage - pageCode + 1;
            } else {
                startpage = 1;
            }
        }
        return new PageIndex(startpage, endpage);
    }
}
