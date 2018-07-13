package cn.mariojd.dto;

/**
 * Created by Mario
 * 分页信息DTO对象
 */
public class PageResult {

    /**
     * 总记录数
     */
    private int totalNumber;
    /**
     * 当前第几页
     */
    private int currentPage;
    /**
     * 每页显示条数
     */
    private int pageNumber = 10;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 数据库中limit的参数，从第几条开始取
     */
    private int dbIndex;
    /**
     * 数据库中limit的参数，一共取多少条
     */
    private int dbNumber;

    /**
     * 根据当前对象中属性值计算并设置相关属性值
     */
    public void count() {
        // 计算总页数
        int totalPageTemp = this.totalNumber / this.pageNumber;
        int plus = (this.totalNumber % this.pageNumber) == 0 ? 0 : 1;
        totalPageTemp = totalPageTemp + plus;
        if (totalPageTemp <= 0) {
            totalPageTemp = 1;
        }
        this.totalPage = totalPageTemp;

        // 设置limit的参数
        this.dbIndex = (this.currentPage - 1) * this.pageNumber;
        this.dbNumber = this.pageNumber;

        // 设置当前页数
        // 总页数小于当前页数，应将当前页数设置为总页数
        if (this.totalPage < this.currentPage) {
            this.currentPage = this.totalPage;
        }
        // 当前页数小于1设置为1
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
    }

    public PageResult getPageResult(PageResult page) {
        if (page.getTotalPage() <= 10) {// >> 总页数不多于10页，则全部显示
            page.setDbIndex(1);
            page.setDbNumber(page.getTotalPage());
        } else {
            // >> 总页数多于10页，则显示当前页附近的共10个页码
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            page.setDbIndex(page.getCurrentPage() - 4);
            page.setDbNumber(page.getCurrentPage() + 5);
            if (page.getDbIndex() < 1) {// 当前面的页码不足4个时，则显示前10个页码
                page.setDbIndex(1);
                page.setDbNumber(10);
            }
            if (page.getDbNumber() > page.getTotalPage()) {// 当后面的页码不足5个时，则显示后10个页码
                page.setDbNumber(page.getTotalPage());
                page.setDbIndex((page.getTotalPage() - 10 + 1));
            }
        }
        return page;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        this.count();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        this.count();
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }

    @Override
    public String toString() {
        return "Page [totalNumber=" + totalNumber + ", currentPage=" + currentPage + ", pageNumber=" + pageNumber
                + ", totalPage=" + totalPage + ", dbIndex=" + dbIndex + ", dbNumber=" + dbNumber + "]";
    }

}
