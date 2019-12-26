package indi.xk.report.utils;


/**
 * 分页返回pageView
 */
public class PagingUtil {
    public static PageView pagingUtil(int count, PageView pageView) {
        if (Utils.isNotEmpty(pageView)) {
            int pageCount = (count % pageView.getPageSize() == 0) ? (count / pageView.getPageSize()) : (count
                    / pageView.getPageSize() + 1);// 总页数
            if (pageCount == 0) {
                pageView.setPageNow(0);
            }

            if (pageView.getPageNow() != 0) {
                pageView.setStartPage((pageView.getPageNow() - 1) * pageView.getPageSize());
                pageView.setPageNow(pageView.getPageNow());
            }
            pageView.setRowCount(count);// 总条数
            pageView.setPageCount(pageCount);// 总页数
            return pageView;
        }
        return pageView;
    }
}
