package cn.ce.servicesupport.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author: ggs
 * @date: 2018-08-13 14:20
 **/
@Getter
@Setter
@NoArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -5586133114941493116L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    protected long total;
    //结果集
    protected List<T> list;

    public Page(List<T> list) {
        fill(list);
    }

    public void fill(List<T> list) {
        if (list != null) {
            this.list = list;
            if (list instanceof com.github.pagehelper.Page) {
                com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
                this.total = page.getTotal();
                this.pageNum = page.getPageNum();
                this.pageSize = page.getPageSize();
            } else if (list instanceof Collection) {
                this.total = list.size();
                this.pageNum = 1;
                this.pageSize = list.size();
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
