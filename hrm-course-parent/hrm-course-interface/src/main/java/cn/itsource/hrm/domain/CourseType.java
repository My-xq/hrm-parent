package cn.itsource.hrm.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程目录
 * </p>
 *
 * @author wangqi
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_type")
public class CourseType extends Model<CourseType> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;

  /**
   * 树形递归
   */
    @TableField(exist = false) //查询所有数据的时候忽略这个字段
    private List<CourseType> children = new ArrayList<>();

  /**
     * 类型名
     */
    private String name;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 图标
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    @TableField("sortIndex")
    private Integer sortIndex;

    /**
     * 路径
     */
    private String path;

    /**
     * 课程数量
     */
    @TableField("totalCount")
    private Integer totalCount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
