package com.atguigu.bean;

import java.util.ArrayList;
import java.util.List;

public class MlbackSlideExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MlbackSlideExample() {
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

        public Criteria andSlideIdIsNull() {
            addCriterion("slide_id is null");
            return (Criteria) this;
        }

        public Criteria andSlideIdIsNotNull() {
            addCriterion("slide_id is not null");
            return (Criteria) this;
        }

        public Criteria andSlideIdEqualTo(Integer value) {
            addCriterion("slide_id =", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdNotEqualTo(Integer value) {
            addCriterion("slide_id <>", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdGreaterThan(Integer value) {
            addCriterion("slide_id >", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_id >=", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdLessThan(Integer value) {
            addCriterion("slide_id <", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdLessThanOrEqualTo(Integer value) {
            addCriterion("slide_id <=", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdIn(List<Integer> values) {
            addCriterion("slide_id in", values, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdNotIn(List<Integer> values) {
            addCriterion("slide_id not in", values, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdBetween(Integer value1, Integer value2) {
            addCriterion("slide_id between", value1, value2, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_id not between", value1, value2, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideNameIsNull() {
            addCriterion("slide_name is null");
            return (Criteria) this;
        }

        public Criteria andSlideNameIsNotNull() {
            addCriterion("slide_name is not null");
            return (Criteria) this;
        }

        public Criteria andSlideNameEqualTo(String value) {
            addCriterion("slide_name =", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameNotEqualTo(String value) {
            addCriterion("slide_name <>", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameGreaterThan(String value) {
            addCriterion("slide_name >", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameGreaterThanOrEqualTo(String value) {
            addCriterion("slide_name >=", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameLessThan(String value) {
            addCriterion("slide_name <", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameLessThanOrEqualTo(String value) {
            addCriterion("slide_name <=", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameLike(String value) {
            addCriterion("slide_name like", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameNotLike(String value) {
            addCriterion("slide_name not like", value, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameIn(List<String> values) {
            addCriterion("slide_name in", values, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameNotIn(List<String> values) {
            addCriterion("slide_name not in", values, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameBetween(String value1, String value2) {
            addCriterion("slide_name between", value1, value2, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideNameNotBetween(String value1, String value2) {
            addCriterion("slide_name not between", value1, value2, "slideName");
            return (Criteria) this;
        }

        public Criteria andSlideAreaIsNull() {
            addCriterion("slide_area is null");
            return (Criteria) this;
        }

        public Criteria andSlideAreaIsNotNull() {
            addCriterion("slide_area is not null");
            return (Criteria) this;
        }

        public Criteria andSlideAreaEqualTo(Integer value) {
            addCriterion("slide_area =", value, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaNotEqualTo(Integer value) {
            addCriterion("slide_area <>", value, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaGreaterThan(Integer value) {
            addCriterion("slide_area >", value, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_area >=", value, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaLessThan(Integer value) {
            addCriterion("slide_area <", value, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaLessThanOrEqualTo(Integer value) {
            addCriterion("slide_area <=", value, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaIn(List<Integer> values) {
            addCriterion("slide_area in", values, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaNotIn(List<Integer> values) {
            addCriterion("slide_area not in", values, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaBetween(Integer value1, Integer value2) {
            addCriterion("slide_area between", value1, value2, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_area not between", value1, value2, "slideArea");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumIsNull() {
            addCriterion("slide_firth_num is null");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumIsNotNull() {
            addCriterion("slide_firth_num is not null");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumEqualTo(Integer value) {
            addCriterion("slide_firth_num =", value, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumNotEqualTo(Integer value) {
            addCriterion("slide_firth_num <>", value, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumGreaterThan(Integer value) {
            addCriterion("slide_firth_num >", value, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_firth_num >=", value, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumLessThan(Integer value) {
            addCriterion("slide_firth_num <", value, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumLessThanOrEqualTo(Integer value) {
            addCriterion("slide_firth_num <=", value, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumIn(List<Integer> values) {
            addCriterion("slide_firth_num in", values, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumNotIn(List<Integer> values) {
            addCriterion("slide_firth_num not in", values, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumBetween(Integer value1, Integer value2) {
            addCriterion("slide_firth_num between", value1, value2, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideFirthNumNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_firth_num not between", value1, value2, "slideFirthNum");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusIsNull() {
            addCriterion("slide_wapstatus is null");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusIsNotNull() {
            addCriterion("slide_wapstatus is not null");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusEqualTo(Integer value) {
            addCriterion("slide_wapstatus =", value, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusNotEqualTo(Integer value) {
            addCriterion("slide_wapstatus <>", value, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusGreaterThan(Integer value) {
            addCriterion("slide_wapstatus >", value, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_wapstatus >=", value, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusLessThan(Integer value) {
            addCriterion("slide_wapstatus <", value, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusLessThanOrEqualTo(Integer value) {
            addCriterion("slide_wapstatus <=", value, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusIn(List<Integer> values) {
            addCriterion("slide_wapstatus in", values, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusNotIn(List<Integer> values) {
            addCriterion("slide_wapstatus not in", values, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusBetween(Integer value1, Integer value2) {
            addCriterion("slide_wapstatus between", value1, value2, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_wapstatus not between", value1, value2, "slideWapstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusIsNull() {
            addCriterion("slide_pcstatus is null");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusIsNotNull() {
            addCriterion("slide_pcstatus is not null");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusEqualTo(Integer value) {
            addCriterion("slide_pcstatus =", value, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusNotEqualTo(Integer value) {
            addCriterion("slide_pcstatus <>", value, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusGreaterThan(Integer value) {
            addCriterion("slide_pcstatus >", value, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_pcstatus >=", value, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusLessThan(Integer value) {
            addCriterion("slide_pcstatus <", value, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusLessThanOrEqualTo(Integer value) {
            addCriterion("slide_pcstatus <=", value, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusIn(List<Integer> values) {
            addCriterion("slide_pcstatus in", values, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusNotIn(List<Integer> values) {
            addCriterion("slide_pcstatus not in", values, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusBetween(Integer value1, Integer value2) {
            addCriterion("slide_pcstatus between", value1, value2, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlidePcstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_pcstatus not between", value1, value2, "slidePcstatus");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlIsNull() {
            addCriterion("slide_wapimgurl is null");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlIsNotNull() {
            addCriterion("slide_wapimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlEqualTo(String value) {
            addCriterion("slide_wapimgurl =", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlNotEqualTo(String value) {
            addCriterion("slide_wapimgurl <>", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlGreaterThan(String value) {
            addCriterion("slide_wapimgurl >", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("slide_wapimgurl >=", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlLessThan(String value) {
            addCriterion("slide_wapimgurl <", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlLessThanOrEqualTo(String value) {
            addCriterion("slide_wapimgurl <=", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlLike(String value) {
            addCriterion("slide_wapimgurl like", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlNotLike(String value) {
            addCriterion("slide_wapimgurl not like", value, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlIn(List<String> values) {
            addCriterion("slide_wapimgurl in", values, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlNotIn(List<String> values) {
            addCriterion("slide_wapimgurl not in", values, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlBetween(String value1, String value2) {
            addCriterion("slide_wapimgurl between", value1, value2, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideWapimgurlNotBetween(String value1, String value2) {
            addCriterion("slide_wapimgurl not between", value1, value2, "slideWapimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlIsNull() {
            addCriterion("slide_pcimgurl is null");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlIsNotNull() {
            addCriterion("slide_pcimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlEqualTo(String value) {
            addCriterion("slide_pcimgurl =", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlNotEqualTo(String value) {
            addCriterion("slide_pcimgurl <>", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlGreaterThan(String value) {
            addCriterion("slide_pcimgurl >", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("slide_pcimgurl >=", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlLessThan(String value) {
            addCriterion("slide_pcimgurl <", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlLessThanOrEqualTo(String value) {
            addCriterion("slide_pcimgurl <=", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlLike(String value) {
            addCriterion("slide_pcimgurl like", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlNotLike(String value) {
            addCriterion("slide_pcimgurl not like", value, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlIn(List<String> values) {
            addCriterion("slide_pcimgurl in", values, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlNotIn(List<String> values) {
            addCriterion("slide_pcimgurl not in", values, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlBetween(String value1, String value2) {
            addCriterion("slide_pcimgurl between", value1, value2, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlidePcimgurlNotBetween(String value1, String value2) {
            addCriterion("slide_pcimgurl not between", value1, value2, "slidePcimgurl");
            return (Criteria) this;
        }

        public Criteria andSlideProidIsNull() {
            addCriterion("slide_proid is null");
            return (Criteria) this;
        }

        public Criteria andSlideProidIsNotNull() {
            addCriterion("slide_proid is not null");
            return (Criteria) this;
        }

        public Criteria andSlideProidEqualTo(Integer value) {
            addCriterion("slide_proid =", value, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidNotEqualTo(Integer value) {
            addCriterion("slide_proid <>", value, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidGreaterThan(Integer value) {
            addCriterion("slide_proid >", value, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_proid >=", value, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidLessThan(Integer value) {
            addCriterion("slide_proid <", value, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidLessThanOrEqualTo(Integer value) {
            addCriterion("slide_proid <=", value, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidIn(List<Integer> values) {
            addCriterion("slide_proid in", values, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidNotIn(List<Integer> values) {
            addCriterion("slide_proid not in", values, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidBetween(Integer value1, Integer value2) {
            addCriterion("slide_proid between", value1, value2, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlideProidNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_proid not between", value1, value2, "slideProid");
            return (Criteria) this;
        }

        public Criteria andSlidePronameIsNull() {
            addCriterion("slide_proname is null");
            return (Criteria) this;
        }

        public Criteria andSlidePronameIsNotNull() {
            addCriterion("slide_proname is not null");
            return (Criteria) this;
        }

        public Criteria andSlidePronameEqualTo(String value) {
            addCriterion("slide_proname =", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameNotEqualTo(String value) {
            addCriterion("slide_proname <>", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameGreaterThan(String value) {
            addCriterion("slide_proname >", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameGreaterThanOrEqualTo(String value) {
            addCriterion("slide_proname >=", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameLessThan(String value) {
            addCriterion("slide_proname <", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameLessThanOrEqualTo(String value) {
            addCriterion("slide_proname <=", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameLike(String value) {
            addCriterion("slide_proname like", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameNotLike(String value) {
            addCriterion("slide_proname not like", value, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameIn(List<String> values) {
            addCriterion("slide_proname in", values, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameNotIn(List<String> values) {
            addCriterion("slide_proname not in", values, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameBetween(String value1, String value2) {
            addCriterion("slide_proname between", value1, value2, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlidePronameNotBetween(String value1, String value2) {
            addCriterion("slide_proname not between", value1, value2, "slideProname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameIsNull() {
            addCriterion("slide_seoname is null");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameIsNotNull() {
            addCriterion("slide_seoname is not null");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameEqualTo(String value) {
            addCriterion("slide_seoname =", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameNotEqualTo(String value) {
            addCriterion("slide_seoname <>", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameGreaterThan(String value) {
            addCriterion("slide_seoname >", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameGreaterThanOrEqualTo(String value) {
            addCriterion("slide_seoname >=", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameLessThan(String value) {
            addCriterion("slide_seoname <", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameLessThanOrEqualTo(String value) {
            addCriterion("slide_seoname <=", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameLike(String value) {
            addCriterion("slide_seoname like", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameNotLike(String value) {
            addCriterion("slide_seoname not like", value, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameIn(List<String> values) {
            addCriterion("slide_seoname in", values, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameNotIn(List<String> values) {
            addCriterion("slide_seoname not in", values, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameBetween(String value1, String value2) {
            addCriterion("slide_seoname between", value1, value2, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideSeonameNotBetween(String value1, String value2) {
            addCriterion("slide_seoname not between", value1, value2, "slideSeoname");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoIsNull() {
            addCriterion("slide_ifinto is null");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoIsNotNull() {
            addCriterion("slide_ifinto is not null");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoEqualTo(Integer value) {
            addCriterion("slide_ifinto =", value, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoNotEqualTo(Integer value) {
            addCriterion("slide_ifinto <>", value, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoGreaterThan(Integer value) {
            addCriterion("slide_ifinto >", value, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_ifinto >=", value, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoLessThan(Integer value) {
            addCriterion("slide_ifinto <", value, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoLessThanOrEqualTo(Integer value) {
            addCriterion("slide_ifinto <=", value, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoIn(List<Integer> values) {
            addCriterion("slide_ifinto in", values, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoNotIn(List<Integer> values) {
            addCriterion("slide_ifinto not in", values, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoBetween(Integer value1, Integer value2) {
            addCriterion("slide_ifinto between", value1, value2, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideIfintoNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_ifinto not between", value1, value2, "slideIfinto");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeIsNull() {
            addCriterion("slide_createTime is null");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeIsNotNull() {
            addCriterion("slide_createTime is not null");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeEqualTo(String value) {
            addCriterion("slide_createTime =", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeNotEqualTo(String value) {
            addCriterion("slide_createTime <>", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeGreaterThan(String value) {
            addCriterion("slide_createTime >", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("slide_createTime >=", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeLessThan(String value) {
            addCriterion("slide_createTime <", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("slide_createTime <=", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeLike(String value) {
            addCriterion("slide_createTime like", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeNotLike(String value) {
            addCriterion("slide_createTime not like", value, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeIn(List<String> values) {
            addCriterion("slide_createTime in", values, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeNotIn(List<String> values) {
            addCriterion("slide_createTime not in", values, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeBetween(String value1, String value2) {
            addCriterion("slide_createTime between", value1, value2, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideCreatetimeNotBetween(String value1, String value2) {
            addCriterion("slide_createTime not between", value1, value2, "slideCreatetime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeIsNull() {
            addCriterion("slide_motifyTime is null");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeIsNotNull() {
            addCriterion("slide_motifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeEqualTo(String value) {
            addCriterion("slide_motifyTime =", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeNotEqualTo(String value) {
            addCriterion("slide_motifyTime <>", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeGreaterThan(String value) {
            addCriterion("slide_motifyTime >", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeGreaterThanOrEqualTo(String value) {
            addCriterion("slide_motifyTime >=", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeLessThan(String value) {
            addCriterion("slide_motifyTime <", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeLessThanOrEqualTo(String value) {
            addCriterion("slide_motifyTime <=", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeLike(String value) {
            addCriterion("slide_motifyTime like", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeNotLike(String value) {
            addCriterion("slide_motifyTime not like", value, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeIn(List<String> values) {
            addCriterion("slide_motifyTime in", values, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeNotIn(List<String> values) {
            addCriterion("slide_motifyTime not in", values, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeBetween(String value1, String value2) {
            addCriterion("slide_motifyTime between", value1, value2, "slideMotifytime");
            return (Criteria) this;
        }

        public Criteria andSlideMotifytimeNotBetween(String value1, String value2) {
            addCriterion("slide_motifyTime not between", value1, value2, "slideMotifytime");
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