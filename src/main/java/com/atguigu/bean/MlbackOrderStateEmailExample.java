package com.atguigu.bean;

import java.util.ArrayList;
import java.util.List;

public class MlbackOrderStateEmailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MlbackOrderStateEmailExample() {
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

        public Criteria andOrderstateemailIdIsNull() {
            addCriterion("orderStateEmail_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdIsNotNull() {
            addCriterion("orderStateEmail_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdEqualTo(Integer value) {
            addCriterion("orderStateEmail_id =", value, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdNotEqualTo(Integer value) {
            addCriterion("orderStateEmail_id <>", value, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdGreaterThan(Integer value) {
            addCriterion("orderStateEmail_id >", value, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderStateEmail_id >=", value, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdLessThan(Integer value) {
            addCriterion("orderStateEmail_id <", value, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdLessThanOrEqualTo(Integer value) {
            addCriterion("orderStateEmail_id <=", value, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdIn(List<Integer> values) {
            addCriterion("orderStateEmail_id in", values, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdNotIn(List<Integer> values) {
            addCriterion("orderStateEmail_id not in", values, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdBetween(Integer value1, Integer value2) {
            addCriterion("orderStateEmail_id between", value1, value2, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orderStateEmail_id not between", value1, value2, "orderstateemailId");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameIsNull() {
            addCriterion("orderStateEmail_name is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameIsNotNull() {
            addCriterion("orderStateEmail_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameEqualTo(String value) {
            addCriterion("orderStateEmail_name =", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameNotEqualTo(String value) {
            addCriterion("orderStateEmail_name <>", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameGreaterThan(String value) {
            addCriterion("orderStateEmail_name >", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameGreaterThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_name >=", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameLessThan(String value) {
            addCriterion("orderStateEmail_name <", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameLessThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_name <=", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameLike(String value) {
            addCriterion("orderStateEmail_name like", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameNotLike(String value) {
            addCriterion("orderStateEmail_name not like", value, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameIn(List<String> values) {
            addCriterion("orderStateEmail_name in", values, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameNotIn(List<String> values) {
            addCriterion("orderStateEmail_name not in", values, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameBetween(String value1, String value2) {
            addCriterion("orderStateEmail_name between", value1, value2, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailNameNotBetween(String value1, String value2) {
            addCriterion("orderStateEmail_name not between", value1, value2, "orderstateemailName");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneIsNull() {
            addCriterion("orderStateEmail_one is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneIsNotNull() {
            addCriterion("orderStateEmail_one is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneEqualTo(String value) {
            addCriterion("orderStateEmail_one =", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneNotEqualTo(String value) {
            addCriterion("orderStateEmail_one <>", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneGreaterThan(String value) {
            addCriterion("orderStateEmail_one >", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneGreaterThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_one >=", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneLessThan(String value) {
            addCriterion("orderStateEmail_one <", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneLessThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_one <=", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneLike(String value) {
            addCriterion("orderStateEmail_one like", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneNotLike(String value) {
            addCriterion("orderStateEmail_one not like", value, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneIn(List<String> values) {
            addCriterion("orderStateEmail_one in", values, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneNotIn(List<String> values) {
            addCriterion("orderStateEmail_one not in", values, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneBetween(String value1, String value2) {
            addCriterion("orderStateEmail_one between", value1, value2, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailOneNotBetween(String value1, String value2) {
            addCriterion("orderStateEmail_one not between", value1, value2, "orderstateemailOne");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoIsNull() {
            addCriterion("orderStateEmail_two is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoIsNotNull() {
            addCriterion("orderStateEmail_two is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoEqualTo(String value) {
            addCriterion("orderStateEmail_two =", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoNotEqualTo(String value) {
            addCriterion("orderStateEmail_two <>", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoGreaterThan(String value) {
            addCriterion("orderStateEmail_two >", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoGreaterThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_two >=", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoLessThan(String value) {
            addCriterion("orderStateEmail_two <", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoLessThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_two <=", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoLike(String value) {
            addCriterion("orderStateEmail_two like", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoNotLike(String value) {
            addCriterion("orderStateEmail_two not like", value, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoIn(List<String> values) {
            addCriterion("orderStateEmail_two in", values, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoNotIn(List<String> values) {
            addCriterion("orderStateEmail_two not in", values, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoBetween(String value1, String value2) {
            addCriterion("orderStateEmail_two between", value1, value2, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailTwoNotBetween(String value1, String value2) {
            addCriterion("orderStateEmail_two not between", value1, value2, "orderstateemailTwo");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeIsNull() {
            addCriterion("orderStateEmail_three is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeIsNotNull() {
            addCriterion("orderStateEmail_three is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeEqualTo(String value) {
            addCriterion("orderStateEmail_three =", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeNotEqualTo(String value) {
            addCriterion("orderStateEmail_three <>", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeGreaterThan(String value) {
            addCriterion("orderStateEmail_three >", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeGreaterThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_three >=", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeLessThan(String value) {
            addCriterion("orderStateEmail_three <", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeLessThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_three <=", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeLike(String value) {
            addCriterion("orderStateEmail_three like", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeNotLike(String value) {
            addCriterion("orderStateEmail_three not like", value, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeIn(List<String> values) {
            addCriterion("orderStateEmail_three in", values, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeNotIn(List<String> values) {
            addCriterion("orderStateEmail_three not in", values, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeBetween(String value1, String value2) {
            addCriterion("orderStateEmail_three between", value1, value2, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailThreeNotBetween(String value1, String value2) {
            addCriterion("orderStateEmail_three not between", value1, value2, "orderstateemailThree");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourIsNull() {
            addCriterion("orderStateEmail_four is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourIsNotNull() {
            addCriterion("orderStateEmail_four is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourEqualTo(String value) {
            addCriterion("orderStateEmail_four =", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourNotEqualTo(String value) {
            addCriterion("orderStateEmail_four <>", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourGreaterThan(String value) {
            addCriterion("orderStateEmail_four >", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourGreaterThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_four >=", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourLessThan(String value) {
            addCriterion("orderStateEmail_four <", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourLessThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_four <=", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourLike(String value) {
            addCriterion("orderStateEmail_four like", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourNotLike(String value) {
            addCriterion("orderStateEmail_four not like", value, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourIn(List<String> values) {
            addCriterion("orderStateEmail_four in", values, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourNotIn(List<String> values) {
            addCriterion("orderStateEmail_four not in", values, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourBetween(String value1, String value2) {
            addCriterion("orderStateEmail_four between", value1, value2, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFourNotBetween(String value1, String value2) {
            addCriterion("orderStateEmail_four not between", value1, value2, "orderstateemailFour");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveIsNull() {
            addCriterion("orderStateEmail_five is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveIsNotNull() {
            addCriterion("orderStateEmail_five is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveEqualTo(String value) {
            addCriterion("orderStateEmail_five =", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveNotEqualTo(String value) {
            addCriterion("orderStateEmail_five <>", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveGreaterThan(String value) {
            addCriterion("orderStateEmail_five >", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveGreaterThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_five >=", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveLessThan(String value) {
            addCriterion("orderStateEmail_five <", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveLessThanOrEqualTo(String value) {
            addCriterion("orderStateEmail_five <=", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveLike(String value) {
            addCriterion("orderStateEmail_five like", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveNotLike(String value) {
            addCriterion("orderStateEmail_five not like", value, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveIn(List<String> values) {
            addCriterion("orderStateEmail_five in", values, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveNotIn(List<String> values) {
            addCriterion("orderStateEmail_five not in", values, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveBetween(String value1, String value2) {
            addCriterion("orderStateEmail_five between", value1, value2, "orderstateemailFive");
            return (Criteria) this;
        }

        public Criteria andOrderstateemailFiveNotBetween(String value1, String value2) {
            addCriterion("orderStateEmail_five not between", value1, value2, "orderstateemailFive");
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