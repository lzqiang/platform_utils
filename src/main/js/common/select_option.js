var SelectOption = function() {

};

/**
 * 菜单级别
 * @return
 */
SelectOption.getPrivLevelData = function getPrivLevelData() {
	var data=new Array();
	data.push({code:1,name:'一级菜单'});
	data.push({code:2,name:'二级菜单'});
	data.push({code:3,name:'三级菜单'});
	return data;
};

SelectOption.loadPrivLevel = function loadPrivLevel(code) {
	var data=SelectOption.getPrivLevelData();
	SelectOption.loadBaseCode(data, code);
};

SelectOption.getPrivLevel=function getPrivLevel(code){
	return SelectOption.getCodeName(SelectOption.getPrivLevelData(),code);
};

/**
 * 行政区域级别
 * @return
 */
SelectOption.getDistrictLevelData = function getDistrictLevelData(){
	var data=new Array();
	data.push({code:1,name:'区县'});
	data.push({code:2,name:'街道办'});
	data.push({code:3,name:'社区居委会'});
	return data;
}

SelectOption.loadDistrictLevel = function loadDistrictLevel(code){
	var data=SelectOption.getDistrictLevelData();
	SelectOption.loadBaseCode(data, code);
}

SelectOption.getDistrictLevel = function getDistrictLevel(code){
	return SelectOption.getCodeName(SelectOption.getDistrictLevelData(),code);
}


/**
 * 行政区域(市、区县、街道办、社区)
 * @return
 */
SelectOption.loadDistrict = function loadDistrict(code,jsonParam){
	SelectOption.loadBaseCodeFromDB(BASE_URL+"/system/sysdistrict/loadDistrictSelect",code,jsonParam);
}

/**
 * 性别
 * @return
 */
SelectOption.getSexData = function getSexData() {
	var data=new Array();
	data.push({code:0,name:'男'});
	data.push({code:1,name:'女'});
	return data;
};
SelectOption.loadSex = function loadSex(code) {
	var data=SelectOption.getSexData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getSex=function getSex(code){
	return SelectOption.getCodeName(SelectOption.getSexData(),code);
};

/**
 * 用户类型
 * @return
 */
SelectOption.getUserTypeData = function getUserTypeData() {
	var data=new Array();
	data.push({code:'SYS',name:'系统'});
	data.push({code:'GOV',name:'政府'});
	data.push({code:'ENT',name:'企业'});
	return data;
};
SelectOption.loadUserType = function loadUserType(code) {
	var data=SelectOption.getUserTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getUserType=function getUserType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getUserTypeData(),code);
};


/**
 * 年份
 * @return
 */
SelectOption.getNowYearData = function getNowYearData() {
	var data=new Array();
	data.push({code:'2013',name:'2013'});
	data.push({code:'2014',name:'2014'});
	data.push({code:'2015',name:'2015'});
	data.push({code:'2016',name:'2016'});
	return data;
};
SelectOption.loadNowYear = function loadNowYear(code) {
	var data=SelectOption.getNowYearData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getNowYear=function getNowYear(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getNowYearData(),code);
};


/**
 * 季度
 * @return
 */
SelectOption.getQuarterData = function getQuarterData() {
	var data=new Array();
	data.push({code:'1',name:'第一季度'});
	data.push({code:'2',name:'第二季度'});
	data.push({code:'3',name:'第三季度'});
	data.push({code:'4',name:'第四季度'});
	return data;
};
SelectOption.loadQuarter = function loadQuarter(code) {
	var data=SelectOption.getQuarterData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getQuarter=function getQuarter(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getQuarterData(),code);
};


/**
 * 月份
 * @return
 */
SelectOption.getMonthData = function getMonthData() {
	var data=new Array();
	data.push({code:'01',name:'1月份'});
	data.push({code:'02',name:'2月份'});
	data.push({code:'03',name:'3月份'});
	data.push({code:'04',name:'4月份'});
	data.push({code:'05',name:'5月份'});
	data.push({code:'06',name:'6月份'});
	data.push({code:'07',name:'7月份'});
	data.push({code:'08',name:'8月份'});
	data.push({code:'09',name:'9月份'});
	data.push({code:'10',name:'10月份'});
	data.push({code:'11',name:'11月份'});
	data.push({code:'12',name:'12月份'});
	return data;
};
SelectOption.loadMonth = function loadMonth(code) {
	var data=SelectOption.getMonthData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getMonth=function getMonth(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getMonthData(),code);
};

/**
 * 行业类型
 * 工业及危险化学品类、商贸及服务类、交通运输类、工程建设类
 * @return
 */
SelectOption.getIndustryTypeData = function getIndustryTypeData() {
	var data=new Array();
	data.push({code:1,name:'工业及危险化学品类'});
	data.push({code:2,name:'商贸及服务类'});
	data.push({code:3,name:'交通运输类'});
	data.push({code:4,name:'工程建设类'});
	return data;
};
SelectOption.loadIndustryType = function loadIndustryType(code) {
	var data=SelectOption.getIndustryTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getIndustryType=function getIndustryType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getIndustryTypeData(),code);
};

/**
 * 行业类型(排查项)
 * 工业及危险化学品类、商贸及服务类、交通运输类、工程建设类、三小场所
 * @return
 */
SelectOption.getCheckIndustryTypeData = function getCheckIndustryTypeData() {
	var data=new Array();
	data.push({code:1,name:'工业及危险化学品类'});
	data.push({code:2,name:'商贸及服务类'});
	data.push({code:3,name:'交通运输类'});
	data.push({code:4,name:'工程建设类'});
	data.push({code:5,name:'三小场所类'});
	return data;
};
SelectOption.loadCheckIndustryType = function loadCheckIndustryType(code) {
	var data=SelectOption.getCheckIndustryTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getCheckIndustryType=function getCheckIndustryType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getCheckIndustryTypeData(),code);
};

/**
 * 地址
 * @return
 */
SelectOption.getAddressTypeData = function getAddressTypeData() {
	var data=new Array();
	data.push({code:'1',name:'办公地址'});
	data.push({code:'2',name:'生产经营地址'});
	return data;
};
SelectOption.loadAddressType = function loadAddressType(code) {
	var data=SelectOption.getAddressTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getAddressType=function getAddressType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getAddressTypeData(),code);
};


/**
 * 分类分级
 * 未分级、A级、B级、C级、D级
 * @return
 */
SelectOption.getClassGradeData = function getClassGradeData() {
	var data=new Array();
	data.push({code:0,name:'未分级'});
	data.push({code:1,name:'A级'});
	data.push({code:2,name:'B级'});
	data.push({code:3,name:'C级'});
	data.push({code:4,name:'D级'});
	return data;
};
SelectOption.loadClassGradeType = function loadClassGradeType(code) {
	var data=SelectOption.getClassGradeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getClassGrade=function getClassGrade(code){
	if(code==null) return "";
	return SelectOption.getCodeName(SelectOption.getClassGradeData(),code);
};



/**
 * 三小类型
 * 小档口、小作坊、小娱乐场所
 * @return
 */
SelectOption.getThreeTypeData = function getThreeTypeData() {
	var data=new Array();
	data.push({code:1,name:'小档口'});
	data.push({code:2,name:'小作坊'});
	data.push({code:3,name:'小娱乐场所'});
	return data;
};
SelectOption.loadThreeType = function loadThreeType(code) {
	var data=SelectOption.getThreeTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getThreeType=function getThreeType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getThreeTypeData(),code);
};

/**
 * 单位管辖隶属关系
 * @return
 */
SelectOption.getSubjectionData = function getSubjectionData() {
	var data=new Array();
	data.push({code:1,name:'市属行业部门管理单位'});
	data.push({code:2,name:'区属行业部门管理单位'});
	data.push({code:3,name:'街镇行业部门管理单位'});
	return data;
};
SelectOption.loadSubjection = function loadSubjection(code) {
	var data=SelectOption.getSubjectionData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getSubjection=function getSubjection(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getSubjectionData,code);
};

/**
 * 企业规模
 * @return
 */
SelectOption.getEntscaleData = function getEntscaleData() {
	var data=new Array();
	data.push({code:1,name:'大型'});
	data.push({code:2,name:'中型'});
	data.push({code:3,name:'小型'});
	data.push({code:4,name:'微型'});
	return data;
};
SelectOption.loadEntscale = function loadEntscale(code) {
	var data=SelectOption.getEntscaleData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getEntscale=function getEntscale(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getEntscaleData,code);
};

/**
 * 评级
 * @return
 */
SelectOption.getRateData = function getRateData() {
	var data=new Array();
	data.push({code:1,name:'一级'});
	data.push({code:2,name:'二级'});
	data.push({code:3,name:'三级'});
	return data;
};
SelectOption.loadRate = function loadRate(code) {
	var data=SelectOption.getRateData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getRate=function getRate(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getRateData(),code);
};


/**
 * 安全生产管理资料(资料类型-大类)
 * @return
 */
SelectOption.getDataOneTypeData = function getDataOneTypeData() {
	var data=new Array();
	data.push({code:1,name:'管理文件类'});
	data.push({code:2,name:'资质证书类'});
	data.push({code:3,name:'企业图例'});
	data.push({code:4,name:'其它'});
	return data;
};
SelectOption.loadDataOneType = function loadDataOneType(code) {
	var data=SelectOption.getDataOneTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getDataOneType=function getDataOneType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getDataOneTypeData(),code);
};

/**
 * 安全生产管理资料(资料类型-小类)
 * @return
 */
SelectOption.getDataTwoTypeData = function getDataTwoTypeData(onetype) {
	var data=new Array();
	if(onetype == 1){
		data.push({code:1,name:'安全生产责任制'});
		data.push({code:2,name:'安全生产管理制度'});
		data.push({code:3,name:'安全生产操作规范'});
	}else if(onetype == 2){
		data.push({code:1,name:'许可证书类'});
		data.push({code:2,name:'资质证书类(包含个人资质)'});
	}else if(onetype == 3){
		data.push({code:1,name:'企业厂区平面图'});
		data.push({code:2,name:'周围环境图'});
		data.push({code:3,name:'安全生产组织架构图'});
	}
	return data;
};
SelectOption.loadDataTwoType = function loadDataTwoType(code,onetype) {
	var data=SelectOption.getDataTwoTypeData(onetype);
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getDataTwoType=function getDataTwoType(code,onetype){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getDataTwoTypeData(onetype),code);
};



/**
 * 安全生产责任划分
 * @return
 */
SelectOption.getSafeResponsibilityData = function getSafeResponsibilityData() {
	var data=new Array();
	data.push({code:1,name:'主要负责人（第一责任人）'});
	data.push({code:2,name:'分管负责人（直接责任人）'});
	return data;
};
SelectOption.loadSafeResponsibility = function loadSafeResponsibility(code) {
	var data=SelectOption.getSafeResponsibilityData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getSafeResponsibility=function getSafeResponsibility(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getSafeResponsibilityData(),code);
};


/**
 * 专职兼职
 * @return
 */
SelectOption.getFullTimePartTimeData = function getFullTimePartTimeData() {
	var data=new Array();
	data.push({code:1,name:'专职'});
	data.push({code:2,name:'兼职'});
	return data;
};
SelectOption.loadFullTimePartTime = function loadFullTimePartTime(code) {
	var data=SelectOption.getFullTimePartTimeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getFullTimePartTime=function getFullTimePartTime(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getFullTimePartTimeData(),code);
};


/**
 * 安全管理人员类型
 * 初级安全主任、中级安全主任、高级安全主任、注册安全工程师、职业健康管理人员、其他
 * @return
 */
SelectOption.getSafeManagerTypeData = function getSafeManagerTypeData() {
	var data=new Array();
	data.push({code:1,name:'初级安全主任'});
	data.push({code:2,name:'中级安全主任'});
	data.push({code:3,name:'高级安全主任'});
	data.push({code:4,name:'注册安全工程师'});
	data.push({code:5,name:'职业健康管理人员'});
	data.push({code:6,name:'其他'});
	return data;
};
SelectOption.loadSafeManagerType = function loadSafeManagerType(code) {
	var data=SelectOption.getSafeManagerTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getSafeManagerType=function getSafeManagerType(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getSafeManagerTypeData(),code);
};


/**
 * 是否
 * @return
 */
SelectOption.getTureFalseData = function getTureFalseData() {
	var data=new Array();
	data.push({code:0,name:'否'});
	data.push({code:1,name:'是'});
	return data;
};
SelectOption.loadTureFalse = function loadTureFalse(code) {
	var data=SelectOption.getTureFalseData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getTureFalse=function getTureFalse(code){
	return SelectOption.getCodeName(SelectOption.getTureFalseData(),code);
};

/**
 * 存储场所
 * @return
 */
SelectOption.getReserveSiteData = function getReserveSiteData() {
	var data=new Array();
	data.push({code:0,name:'无'});
	data.push({code:1,name:'储罐区储存'});
	data.push({code:2,name:'专用仓库储存'});
	data.push({code:3,name:'专用场地储存'});
	return data;
};
SelectOption.loadReserveSite = function loadReserveSite(code) {
	var data=SelectOption.getReserveSiteData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getReserveSite=function getReserveSite(code){
	return SelectOption.getCodeName(SelectOption.getReserveSiteData(),code);
};

/**
 * 存储方式
 * @return
 */
SelectOption.getReserveWayData = function getReserveWayData() {
	var data=new Array();
	data.push({code:0,name:'无'});
	data.push({code:1,name:'储罐储存'});
	data.push({code:2,name:'分离储存'});
	data.push({code:3,name:'隔离储存'});
	data.push({code:4,name:'隔开储存'});
	return data;
};
SelectOption.loadReserveWay = function loadReserveWay(code) {
	var data=SelectOption.getReserveWayData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getReserveWay=function getReserveWay(code){
	return SelectOption.getCodeName(SelectOption.getReserveWayData(),code);
};

/**
 * 存储方式
 * @return
 */
SelectOption.getIndustryTypesData = function getIndustryTypesData() {
	var data=new Array();
	data.push({code:0,name:'电力'});
	data.push({code:1,name:'危化品'});
	data.push({code:2,name:'工商贸'});
	data.push({code:3,name:'其他'});
	return data;
};
SelectOption.loadIndustryTypes = function loadIndustryTypes(code) {
	var data=SelectOption.getIndustryTypesData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getIndustryTypes=function getIndustryTypes(code){
	return SelectOption.getCodeName(SelectOption.getIndustryTypesData(),code);
};


/**
 * 用人单位类型
 * @return
 */
SelectOption.getEntTypeData = function getEntTypeData() {
	var data=new Array();
	data.push({code:1,name:'个体工商户'});
	data.push({code:2,name:'产业活动单位'});
	data.push({code:3,name:'企业法人单位'});
	return data;
};
SelectOption.loadEntType = function loadEntType(code) {
	var data=SelectOption.getEntTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getEntType=function getEntType(code){
	return SelectOption.getCodeName(SelectOption.getEntTypeData(),code);
};

/**
 * 定期进行健康体检人员比例
 * @return
 */
SelectOption.getPhysicalsData = function getPhysicalsData() {
	var data=new Array();
	data.push({code:1,name:'全部'});
	data.push({code:2,name:'部分'});
	data.push({code:3,name:'无'});
	return data;
};
SelectOption.loadPhysicals = function loadPhysicals(code) {
	var data=SelectOption.getPhysicalsData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getPhysicals=function getPhysicals(code){
	return SelectOption.getCodeName(SelectOption.getPhysicalsData(),code);
};

/**
 * 职业危害岗位
 * @return
 */
SelectOption.getProharmPostData = function getProharmPostData() {
	var data=new Array();
	data.push({code:1,name:'自动化'});
	data.push({code:2,name:'机械化'});
	data.push({code:3,name:'手工操作'});
	return data;
};
SelectOption.loadProharmPost = function loadProharmPost(code) {
	var data=SelectOption.getProharmPostData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getProharmPost=function getProharmPost(code){
	return SelectOption.getCodeName(SelectOption.getProharmPostData(),code);
};


/**
 * 单位性质
 * @return
 */
SelectOption.getUnitEntnatData = function getUnitEntnatData() {
	var data=new Array();
	data.push({code:1,name:'央企'});
	data.push({code:2,name:'省属企业'});
	data.push({code:3,name:'其他'});
	return data;
};
SelectOption.loadUnitEntnat = function loadUnitEntnat(code) {
	var data=SelectOption.getUnitEntnatData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getUnitEntnat=function getUnitEntnat(code){
	return SelectOption.getCodeName(SelectOption.getUnitEntnatData(),code);
};

/**
 * 单位法人性质
 * @return
 */
SelectOption.getUnitPernatData = function getUnitPernatData() {
	var data=new Array();
	data.push({code:1,name:'独立法人'});
	data.push({code:2,name:'非独立法人'});
	return data;
};
SelectOption.loadUnitPernat = function loadUnitPernat(code) {
	var data=SelectOption.getUnitPernatData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getUnitPernat=function getUnitPernat(code){
	return SelectOption.getCodeName(SelectOption.getUnitPernatData(),code);
};

/**
 * 运行状态
 * @return
 */
SelectOption.getRunStatusData = function getRunStatusData() {
	var data=new Array();
	data.push({code:1,name:'自动化'});
	data.push({code:2,name:'机械化'});
	data.push({code:3,name:'手工操作'});
	return data;
};
SelectOption.loadRunStatus = function loadRunStatus(code) {
	var data=SelectOption.getRunStatusData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getRunStatus=function getRunStatus(code){
	return SelectOption.getCodeName(SelectOption.getRunStatusData(),code);
};


/**
 * 存放物体形态
 * @return
 */
SelectOption.getFormStateData = function getFormStateData() {
	var data=new Array();
	data.push({code:1,name:'固体'});
	data.push({code:2,name:'液体'});
	data.push({code:3,name:'气体'});
	return data;
};
SelectOption.loadFormState = function loadFormState(code) {
	var data=SelectOption.getFormStateData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getFormState=function getFormState(code){
	return SelectOption.getCodeName(SelectOption.getFormStateData(),code);
};

/**
 * 储罐类型
 * @return
 */
SelectOption.getStotankTypeData = function getStotankTypeData() {
	var data=new Array();
	data.push({code:1,name:'固体顶储罐'});
	data.push({code:2,name:'浮顶储罐'});
	data.push({code:3,name:'其他类型储罐'});
	return data;
};
SelectOption.loadStotankType = function loadStotankType(code) {
	var data=SelectOption.getStotankTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getStotankType=function getStotankType(code){
	return SelectOption.getCodeName(SelectOption.getStotankTypeData(),code);
};

/**
 * 许可预警状态
 * @return
 */
SelectOption.getPermitStatusData = function getPermitStatusData() {
	var data=new Array();
	data.push({code:1,name:'超期'});
	data.push({code:2,name:'预超期'});
	data.push({code:3,name:'正常'});
	return data;
};
SelectOption.loadPermitStatus = function loadPermitStatus(code) {
	var data=SelectOption.getPermitStatusData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getPermitStatus=function getPermitStatus(code){
	return SelectOption.getCodeName(SelectOption.getPermitStatusData(),code);
};

/**
 * 重大危险源状态
 */
SelectOption.getDangerSourceStateData = function getDangerSourceStateData() {
	var data=new Array();
	data.push({code:"01",name:'已登记'});
	data.push({code:"02",name:'已上报'});
	data.push({code:"03",name:'同意受理'});
	data.push({code:"04",name:'不同意受理'});
	data.push({code:"05",name:'同意备案'});
	data.push({code:"06",name:'不同意备案'});
	data.push({code:"07",name:'核销申请'});
	data.push({code:"08",name:'核销受理'});
	data.push({code:"09",name:'核销受理驳回'});
	data.push({code:"10",name:'已核销'});
	data.push({code:"11",name:'核销审核驳回'});
	return data;
};
SelectOption.loadDangerSourceState = function loadDangerSourceState(code) {
	var data=SelectOption.getDangerSourceStateData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getDangerSourceState=function getDangerSourceState(code){
	return SelectOption.getCodeName(SelectOption.getDangerSourceStateData(),code);
};

/**
 * 重大危险源级别
 */
SelectOption.getdangSouLevelData = function getdangSouLevelData() {
	var data=new Array();
	data.push({code:1,name:'一级'});
	data.push({code:2,name:'二级'});
	data.push({code:3,name:'三级'});
	data.push({code:4,name:'四级'});
	return data;
};
SelectOption.loaddangSouLevel = function loaddangSouLevel(code) {
	var data=SelectOption.getdangSouLevelData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getdangSouLevel=function getdangSouLevel(code){
	return SelectOption.getCodeName(SelectOption.getdangSouLevelData(),code);
};
/**
 * 是否是重大危险源
 */
SelectOption.getisDangSouData = function getisDangSouData() {
	var data=new Array();
	data.push({code:1,name:'是'});
	data.push({code:0,name:'否'});
	return data;
};
SelectOption.loadisDangSou = function loadisDangSou(code) {
	var data=SelectOption.getisDangSouData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getisDangSou=function getisDangSou(code){
	return SelectOption.getCodeName(SelectOption.getisDangSouData(),code);
};


/**
 * 重大危险源类别
 */
SelectOption.getDangerTypeData = function getDangerTypeData() {
	var data=new Array();
	data.push({code:1,name:'危险化学品类'});
	data.push({code:2,name:'燃气类'});
	data.push({code:3,name:'港口类'});
	return data;
};
SelectOption.loadDangerType = function loadDangerType(code) {
	var data=SelectOption.getDangerTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getDangerType=function getDangerType(code){
	return SelectOption.getCodeName(SelectOption.getDangerTypeData(),code);
};

/**
 * 排查分类
 */
SelectOption.getCheckItemTypeData = function getCheckItemTypeData() {
	var data=new Array();
	data.push({code:1,name:'通用基础检查项'});
	data.push({code:2,name:'通用现场检查项'});
	data.push({code:3,name:'专用检查项'});
	return data;
};
SelectOption.loadCheckItemType = function loadCheckItemType(code) {
	var data=SelectOption.getCheckItemTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getCheckItemType=function getCheckItemType(code){
	return SelectOption.getCodeName(SelectOption.getCheckItemTypeData(),code);
};


/**
 * 隐患上报状态
 * @return
 */
SelectOption.getReportstatusData = function getReportstatusData() {
	var data=new Array();
	data.push({code:'1',name:'已上报'});
	data.push({code:'0',name:'未上报'});
	return data;
};
SelectOption.loadReportstatus = function loadReportstatus(code) {
	var data=SelectOption.getReportstatusData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getReportstatus=function getReportstatus(code){
	if(code==null)
		return "";
	return SelectOption.getCodeName(SelectOption.getReportstatusData(),code);
};


/**
 * 巡查人员身份
 */
SelectOption.getPatrollerTypeData = function getPatrollerTypeData() {
	var data=new Array();
	data.push({code:1,name:'巡查登记人员'});
	data.push({code:2,name:'网格管理人员'});
	return data;
};
SelectOption.loadPatrollerType = function loadPatrollerType(code) {
	var data=SelectOption.getPatrollerTypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getPatrollerType=function getPatrollerType(code){
	return SelectOption.getCodeName(SelectOption.getPatrollerTypeData(),code);
};


/**
 * 隐患排查结果
 */
SelectOption.getCheckResultData = function getCheckResultData() {
	var data=new Array();
	data.push({code:0,name:'无隐患'});
	data.push({code:1,name:'有隐患'});
	return data;
};
SelectOption.loadCheckResult = function loadCheckResult(code) {
	var data=SelectOption.getCheckResultData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getCheckResult=function getCheckResult(code){
	return SelectOption.getCodeName(SelectOption.getCheckResultData(),code);
};

/**
 * 危险源备案申请类型
 * @return
 */
SelectOption.getCaseapplytypeData = function getCaseapplytypeData() {
	var data=new Array();
	data.push({code:0,name:'初次备案'});
	data.push({code:1,name:'新项目备案'});
	data.push({code:2,name:'改项目备案'});
	data.push({code:3,name:'扩项目备案'});
	data.push({code:4,name:'更新备案'});
	return data;
};
SelectOption.loadCaseapplytype = function loadCaseapplytype(code) {
	var data=SelectOption.getCaseapplytypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getCaseapplytype=function getCaseapplytype(code){
	return SelectOption.getCodeName(SelectOption.getCaseapplytypeData(),code);
};

/**
 * 危险化学品单位类型
 * @return
 */
SelectOption.getDangerchemcomptypeData = function getDangerchemcomptypeData() {
	var data=new Array();
	data.push({code:0,name:'生产'});
	data.push({code:1,name:'储存'});
	data.push({code:2,name:'使用'});
	data.push({code:3,name:'经营'});
	data.push({code:4,name:'其他'});
	return data;
};
SelectOption.loadDangerchemcomptype = function loadDangerchemcomptype(code) {
	var data=SelectOption.getDangerchemcomptypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getDangerchemcomptype=function getDangerchemcomptype(code){
	return SelectOption.getCodeName(SelectOption.getDangerchemcomptypeData(),code);
};

/**
 * 经济类型
 * @return
 */
SelectOption.getEconomytypeData = function getEconomytypeData() {
	var data=new Array();
	data.push({code:0,name:'国有经济'});
	data.push({code:1,name:'集体经济'});
	data.push({code:2,name:'私营经济'});
	data.push({code:3,name:'有限责任公司'});
	data.push({code:4,name:'联营经济'});
	data.push({code:5,name:'股份合作'});
	data.push({code:6,name:'外商投资'});
	data.push({code:7,name:'港澳台投资'});
	data.push({code:8,name:'其他经济'});
	return data;
};
SelectOption.loadEconomytype = function loadEconomytype(code) {
	var data=SelectOption.getEconomytypeData();
	SelectOption.loadBaseCode(data, code);
};
SelectOption.getEconomytype=function getEconomytype(code){
	return SelectOption.getCodeName(SelectOption.getEconomytypeData(),code);
};


/************************************************* base start*******************************************************************************/
/**
 * 加载code
 * 
 * @param loadurl
 *            访问地址
 * @param code
 *            界面ID
 * @return
 */
SelectOption.loadBaseCodeFromDB = function loadBaseCodeFromDB(loadurl, code,jsonParam) {
	var value = $('#' + code).attr("selectvalue");
	$.ajax( {
		type : "post",
		cache : false,
		url : loadurl,
		data: jsonParam,
		dataType : 'json',
		success : function(json) {
			if (json.length > 0) {
				var o = new Option('请选择', '');
				$("#" + code)[0].options.add(o);
				for ( var i = 0; i < json.length; i++) {
					var t = new Option(json[i].name, json[i].code);
					$("#" + code)[0].options.add(t);
						if (value == json[i].code) {
							$("#" + code).val(value);
						}
				}
			}
		}
	});
};

/**
 * 加载code
 * 
 * @param data
 *            数据
 * @param code
 *            界面ID
 * @return
 */
SelectOption.loadBaseCode = function loadBaseCode(data, code) {
	var value = $('#' + code).attr("selectvalue");
	if (data.length > 0) {
		var o = new Option('请选择', '');
		$("#" + code)[0].options.add(o);

		for ( var i = 0; i < data.length; i++) {
			var t = new Option(data[i].name, data[i].code);
			$("#" + code)[0].options.add(t);
			if (value) {
				if (value == data[i].code) {
					$("#" + code).val(value);
				}
			}
		}
	}
};


SelectOption.getCodeName=function getCodeName(data,code){
	for(var i=0;i<data.length;i++){
		if(data[i].code==code){
			return data[i].name;
		}
	}
};

/************************************************* base end*******************************************************************************/
