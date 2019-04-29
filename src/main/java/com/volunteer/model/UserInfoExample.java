package com.volunteer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserInfoExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andWorkerIsNull() {
            addCriterion("worker is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIsNotNull() {
            addCriterion("worker is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerEqualTo(String value) {
            addCriterion("worker =", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotEqualTo(String value) {
            addCriterion("worker <>", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerGreaterThan(String value) {
            addCriterion("worker >", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerGreaterThanOrEqualTo(String value) {
            addCriterion("worker >=", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLessThan(String value) {
            addCriterion("worker <", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLessThanOrEqualTo(String value) {
            addCriterion("worker <=", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLike(String value) {
            addCriterion("worker like", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotLike(String value) {
            addCriterion("worker not like", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerIn(List<String> values) {
            addCriterion("worker in", values, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotIn(List<String> values) {
            addCriterion("worker not in", values, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerBetween(String value1, String value2) {
            addCriterion("worker between", value1, value2, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotBetween(String value1, String value2) {
            addCriterion("worker not between", value1, value2, "worker");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneIsNull() {
            addCriterion("login_phone is null");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneIsNotNull() {
            addCriterion("login_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneEqualTo(String value) {
            addCriterion("login_phone =", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneNotEqualTo(String value) {
            addCriterion("login_phone <>", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneGreaterThan(String value) {
            addCriterion("login_phone >", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("login_phone >=", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneLessThan(String value) {
            addCriterion("login_phone <", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneLessThanOrEqualTo(String value) {
            addCriterion("login_phone <=", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneLike(String value) {
            addCriterion("login_phone like", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneNotLike(String value) {
            addCriterion("login_phone not like", value, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneIn(List<String> values) {
            addCriterion("login_phone in", values, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneNotIn(List<String> values) {
            addCriterion("login_phone not in", values, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneBetween(String value1, String value2) {
            addCriterion("login_phone between", value1, value2, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andLoginPhoneNotBetween(String value1, String value2) {
            addCriterion("login_phone not between", value1, value2, "loginPhone");
            return (Criteria) this;
        }

        public Criteria andDescptionIsNull() {
            addCriterion("descption is null");
            return (Criteria) this;
        }

        public Criteria andDescptionIsNotNull() {
            addCriterion("descption is not null");
            return (Criteria) this;
        }

        public Criteria andDescptionEqualTo(String value) {
            addCriterion("descption =", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionNotEqualTo(String value) {
            addCriterion("descption <>", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionGreaterThan(String value) {
            addCriterion("descption >", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionGreaterThanOrEqualTo(String value) {
            addCriterion("descption >=", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionLessThan(String value) {
            addCriterion("descption <", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionLessThanOrEqualTo(String value) {
            addCriterion("descption <=", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionLike(String value) {
            addCriterion("descption like", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionNotLike(String value) {
            addCriterion("descption not like", value, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionIn(List<String> values) {
            addCriterion("descption in", values, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionNotIn(List<String> values) {
            addCriterion("descption not in", values, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionBetween(String value1, String value2) {
            addCriterion("descption between", value1, value2, "descption");
            return (Criteria) this;
        }

        public Criteria andDescptionNotBetween(String value1, String value2) {
            addCriterion("descption not between", value1, value2, "descption");
            return (Criteria) this;
        }

        public Criteria andHobbyIsNull() {
            addCriterion("hobby is null");
            return (Criteria) this;
        }

        public Criteria andHobbyIsNotNull() {
            addCriterion("hobby is not null");
            return (Criteria) this;
        }

        public Criteria andHobbyEqualTo(String value) {
            addCriterion("hobby =", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotEqualTo(String value) {
            addCriterion("hobby <>", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyGreaterThan(String value) {
            addCriterion("hobby >", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyGreaterThanOrEqualTo(String value) {
            addCriterion("hobby >=", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyLessThan(String value) {
            addCriterion("hobby <", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyLessThanOrEqualTo(String value) {
            addCriterion("hobby <=", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyLike(String value) {
            addCriterion("hobby like", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotLike(String value) {
            addCriterion("hobby not like", value, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyIn(List<String> values) {
            addCriterion("hobby in", values, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotIn(List<String> values) {
            addCriterion("hobby not in", values, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyBetween(String value1, String value2) {
            addCriterion("hobby between", value1, value2, "hobby");
            return (Criteria) this;
        }

        public Criteria andHobbyNotBetween(String value1, String value2) {
            addCriterion("hobby not between", value1, value2, "hobby");
            return (Criteria) this;
        }

        public Criteria andUserPicIsNull() {
            addCriterion("user_pic is null");
            return (Criteria) this;
        }

        public Criteria andUserPicIsNotNull() {
            addCriterion("user_pic is not null");
            return (Criteria) this;
        }

        public Criteria andUserPicEqualTo(String value) {
            addCriterion("user_pic =", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicNotEqualTo(String value) {
            addCriterion("user_pic <>", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicGreaterThan(String value) {
            addCriterion("user_pic >", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicGreaterThanOrEqualTo(String value) {
            addCriterion("user_pic >=", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicLessThan(String value) {
            addCriterion("user_pic <", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicLessThanOrEqualTo(String value) {
            addCriterion("user_pic <=", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicLike(String value) {
            addCriterion("user_pic like", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicNotLike(String value) {
            addCriterion("user_pic not like", value, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicIn(List<String> values) {
            addCriterion("user_pic in", values, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicNotIn(List<String> values) {
            addCriterion("user_pic not in", values, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicBetween(String value1, String value2) {
            addCriterion("user_pic between", value1, value2, "userPic");
            return (Criteria) this;
        }

        public Criteria andUserPicNotBetween(String value1, String value2) {
            addCriterion("user_pic not between", value1, value2, "userPic");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Date value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Date value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Date value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Date value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Date value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Date value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Date> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Date> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Date value1, Date value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Date value1, Date value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Long value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Long value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Long value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Long value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Long value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Long value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Long> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Long> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Long value1, Long value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Long value1, Long value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andLifecycleIsNull() {
            addCriterion("lifecycle is null");
            return (Criteria) this;
        }

        public Criteria andLifecycleIsNotNull() {
            addCriterion("lifecycle is not null");
            return (Criteria) this;
        }

        public Criteria andLifecycleEqualTo(Integer value) {
            addCriterion("lifecycle =", value, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleNotEqualTo(Integer value) {
            addCriterion("lifecycle <>", value, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleGreaterThan(Integer value) {
            addCriterion("lifecycle >", value, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleGreaterThanOrEqualTo(Integer value) {
            addCriterion("lifecycle >=", value, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleLessThan(Integer value) {
            addCriterion("lifecycle <", value, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleLessThanOrEqualTo(Integer value) {
            addCriterion("lifecycle <=", value, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleIn(List<Integer> values) {
            addCriterion("lifecycle in", values, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleNotIn(List<Integer> values) {
            addCriterion("lifecycle not in", values, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleBetween(Integer value1, Integer value2) {
            addCriterion("lifecycle between", value1, value2, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andLifecycleNotBetween(Integer value1, Integer value2) {
            addCriterion("lifecycle not between", value1, value2, "lifecycle");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Long value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Long value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Long value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Long value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Long> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Long> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andGroupIsNull() {
            addCriterion("group is null");
            return (Criteria) this;
        }

        public Criteria andGroupIsNotNull() {
            addCriterion("group is not null");
            return (Criteria) this;
        }

        public Criteria andGroupEqualTo(Integer value) {
            addCriterion("group =", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(Integer value) {
            addCriterion("group <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(Integer value) {
            addCriterion("group >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("group >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(Integer value) {
            addCriterion("group <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(Integer value) {
            addCriterion("group <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupIn(List<Integer> values) {
            addCriterion("group in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotIn(List<Integer> values) {
            addCriterion("group not in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupBetween(Integer value1, Integer value2) {
            addCriterion("group between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("group not between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderIsNull() {
            addCriterion("is_group_leader is null");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderIsNotNull() {
            addCriterion("is_group_leader is not null");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderEqualTo(Boolean value) {
            addCriterion("is_group_leader =", value, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderNotEqualTo(Boolean value) {
            addCriterion("is_group_leader <>", value, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderGreaterThan(Boolean value) {
            addCriterion("is_group_leader >", value, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_group_leader >=", value, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderLessThan(Boolean value) {
            addCriterion("is_group_leader <", value, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderLessThanOrEqualTo(Boolean value) {
            addCriterion("is_group_leader <=", value, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderIn(List<Boolean> values) {
            addCriterion("is_group_leader in", values, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderNotIn(List<Boolean> values) {
            addCriterion("is_group_leader not in", values, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderBetween(Boolean value1, Boolean value2) {
            addCriterion("is_group_leader between", value1, value2, "isGroupLeader");
            return (Criteria) this;
        }

        public Criteria andIsGroupLeaderNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_group_leader not between", value1, value2, "isGroupLeader");
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