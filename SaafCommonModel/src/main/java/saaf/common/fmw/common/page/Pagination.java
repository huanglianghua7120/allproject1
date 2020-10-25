package saaf.common.fmw.common.page;

import java.util.List;

public class Pagination<T> {
    private long preIndex;
    private long curIndex;
    private long nextIndex;
    private long pageSize;
    private long count;
    private long pagesCount;
    private List<T> data;

    public void setPreIndex(long preIndex) {
        this.preIndex = preIndex;
    }

    public void setCurIndex(long curIndex) {
        this.curIndex = curIndex;
    }

    public void setNextIndex(long nextIndex) {
        this.nextIndex = nextIndex;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public Pagination() {
        updateInfo(0L, 0L, 0L);
    }

    public Pagination(long pageIndex, long pageSize) {
        updateInfo(pageIndex, pageSize, 0L);
    }

    public Pagination(long pageIndex, long pageSize, long rowsCount) {
        updateInfo(pageIndex, pageSize, rowsCount);
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> items) {
        this.data = items;
    }

    public long getCurIndex() {
        return this.curIndex;
    }

    public long getNextIndex() {
        return this.nextIndex;
    }

    public long getPagesCount() {
        return this.pagesCount;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public long getPreIndex() {
        return this.preIndex;
    }

    public long getCount() {
        return this.count;
    }

    public long getFirstIndex() {
        return 1L;
    }

    public long getLastIndex() {
        return this.pagesCount;
    }

    private void updateInfo(long pageIndex, long pageSize, long rowsCount) {
        if (pageSize > 0L) {
            this.curIndex = pageIndex;
            this.count = rowsCount;
            this.pageSize = pageSize;

            this.pagesCount = ((rowsCount + pageSize - 1L) / pageSize);

            if (this.curIndex <= 0L)
                this.curIndex = 1L;
            if (this.curIndex > this.pagesCount) {
                this.curIndex = this.pagesCount;
            }
            this.nextIndex = (this.curIndex + 1L);
            if (this.nextIndex > this.pagesCount) {
                this.nextIndex = this.pagesCount;
            }
            this.preIndex = (this.curIndex - 1L);
            if (this.preIndex <= 0L)
                this.preIndex = 1L;
        } else {
            this.preIndex = 1L;
            this.curIndex = 1L;
            this.nextIndex = 1L;
            this.pageSize = 0L;
            this.pagesCount = 1L;
        }
    }

    public void setCount(long rowsCount) {
        updateInfo(this.curIndex, this.pageSize, rowsCount);
    }

    public void setPagesCount(long pagesCount) {
        this.pagesCount = pagesCount;
    }
}
