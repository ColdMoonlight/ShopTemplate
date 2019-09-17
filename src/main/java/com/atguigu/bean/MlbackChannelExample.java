package com.atguigu.bean;

import java.util.ArrayList;
import java.util.List;

public class MlbackChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MlbackChannelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Integer value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Integer value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Integer value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Integer value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Integer value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Integer> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Integer> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Integer value1, Integer value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelUnameIsNull() {
            addCriterion("channel_uname is null");
            return (Criteria) this;
        }

        public Criteria andChannelUnameIsNotNull() {
            addCriterion("channel_uname is not null");
            return (Criteria) this;
        }

        public Criteria andChannelUnameEqualTo(String value) {
            addCriterion("channel_uname =", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameNotEqualTo(String value) {
            addCriterion("channel_uname <>", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameGreaterThan(String value) {
            addCriterion("channel_uname >", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_uname >=", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameLessThan(String value) {
            addCriterion("channel_uname <", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameLessThanOrEqualTo(String value) {
            addCriterion("channel_uname <=", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameLike(String value) {
            addCriterion("channel_uname like", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameNotLike(String value) {
            addCriterion("channel_uname not like", value, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameIn(List<String> values) {
            addCriterion("channel_uname in", values, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameNotIn(List<String> values) {
            addCriterion("channel_uname not in", values, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameBetween(String value1, String value2) {
            addCriterion("channel_uname between", value1, value2, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelUnameNotBetween(String value1, String value2) {
            addCriterion("channel_uname not between", value1, value2, "channelUname");
            return (Criteria) this;
        }

        public Criteria andChannelNumberIsNull() {
            addCriterion("channel_number is null");
            return (Criteria) this;
        }

        public Criteria andChannelNumberIsNotNull() {
            addCriterion("channel_number is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNumberEqualTo(String value) {
            addCriterion("channel_number =", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberNotEqualTo(String value) {
            addCriterion("channel_number <>", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberGreaterThan(String value) {
            addCriterion("channel_number >", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("channel_number >=", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberLessThan(String value) {
            addCriterion("channel_number <", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberLessThanOrEqualTo(String value) {
            addCriterion("channel_number <=", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberLike(String value) {
            addCriterion("channel_number like", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberNotLike(String value) {
            addCriterion("channel_number not like", value, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberIn(List<String> values) {
            addCriterion("channel_number in", values, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberNotIn(List<String> values) {
            addCriterion("channel_number not in", values, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberBetween(String value1, String value2) {
            addCriterion("channel_number between", value1, value2, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelNumberNotBetween(String value1, String value2) {
            addCriterion("channel_number not between", value1, value2, "channelNumber");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeIsNull() {
            addCriterion("channel_createTime is null");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeIsNotNull() {
            addCriterion("channel_createTime is not null");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeEqualTo(String value) {
            addCriterion("channel_createTime =", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeNotEqualTo(String value) {
            addCriterion("channel_createTime <>", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeGreaterThan(String value) {
            addCriterion("channel_createTime >", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_createTime >=", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeLessThan(String value) {
            addCriterion("channel_createTime <", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("channel_createTime <=", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeLike(String value) {
            addCriterion("channel_createTime like", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeNotLike(String value) {
            addCriterion("channel_createTime not like", value, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeIn(List<String> values) {
            addCriterion("channel_createTime in", values, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeNotIn(List<String> values) {
            addCriterion("channel_createTime not in", values, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeBetween(String value1, String value2) {
            addCriterion("channel_createTime between", value1, value2, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelCreatetimeNotBetween(String value1, String value2) {
            addCriterion("channel_createTime not between", value1, value2, "channelCreatetime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeIsNull() {
            addCriterion("channel_motifyTime is null");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeIsNotNull() {
            addCriterion("channel_motifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeEqualTo(String value) {
            addCriterion("channel_motifyTime =", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeNotEqualTo(String value) {
            addCriterion("channel_motifyTime <>", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeGreaterThan(String value) {
            addCriterion("channel_motifyTime >", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_motifyTime >=", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeLessThan(String value) {
            addCriterion("channel_motifyTime <", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeLessThanOrEqualTo(String value) {
            addCriterion("channel_motifyTime <=", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeLike(String value) {
            addCriterion("channel_motifyTime like", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeNotLike(String value) {
            addCriterion("channel_motifyTime not like", value, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeIn(List<String> values) {
            addCriterion("channel_motifyTime in", values, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeNotIn(List<String> values) {
            addCriterion("channel_motifyTime not in", values, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeBetween(String value1, String value2) {
            addCriterion("channel_motifyTime between", value1, value2, "channelMotifytime");
            return (Criteria) this;
        }

        public Criteria andChannelMotifytimeNotBetween(String value1, String value2) {
            addCriterion("channel_motifyTime not between", value1, value2, "channelMotifytime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}