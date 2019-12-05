package com.ffy.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客实体类
 */
/*
    @Entity 标记当前是一个和数据库对应的实体类
    @Table(name = "t_blog") 对应数据库的表名字
    @Id 声明当前此字段是主键
    @GeneratedValue 自动生成策略
    @Temporal(TemporalType.TIMESTAMP) 对应数据库的时间格式
    @ManyToOne 代表关系 一对多
    @ManyToMany 代表关系 多对多
    @ManyToMany(cascade = {CascadeType.PERSIST}) 级联新增，表示blog发布，如果tag是一个新的标签，也被更新到数据库
 */
@Entity
@Table(name = "t_blog")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //首图
    private String firstPicture;
    //原创、转载、翻译
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏
    private boolean appreciationStatus;
    //转载
    private boolean shareStatementStatus;
    //评论
    private boolean commendableStatus;
    //发布、保存
    private boolean publishedStatus;
    //推荐
    private boolean recommendStatus;
    //描述
    private String description;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();


    /*
        标签 id 值 字符串
     */
    @Transient
    private String tagIds;

    /*
        标签 转换为 Spring
     */
    public void init() {
        this.tagIds = tagsTaIds(this.getTags());
    }
    /*
        字符串转换
     */
    private String tagsTaIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciationStatus() {
        return appreciationStatus;
    }

    public void setAppreciationStatus(boolean appreciationStatus) {
        this.appreciationStatus = appreciationStatus;
    }

    public boolean isShareStatementStatus() {
        return shareStatementStatus;
    }

    public void setShareStatementStatus(boolean shareStatementStatus) {
        this.shareStatementStatus = shareStatementStatus;
    }

    public boolean isCommendableStatus() {
        return commendableStatus;
    }

    public void setCommendableStatus(boolean commendableStatus) {
        this.commendableStatus = commendableStatus;
    }

    public boolean isPublishedStatus() {
        return publishedStatus;
    }

    public void setPublishedStatus(boolean publishedStatus) {
        this.publishedStatus = publishedStatus;
    }

    public boolean isRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(boolean recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
