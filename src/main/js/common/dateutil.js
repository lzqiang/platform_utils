//扩展Date的format方法 
Date.prototype.format = function (format) { 
	var o = { 
	"M+": this.getMonth() + 1, 
	"d+": this.getDate(), 
	"h+": this.getHours(), 
	"m+": this.getMinutes(), 
	"s+": this.getSeconds(), 
	"q+": Math.floor((this.getMonth() + 3) / 3), 
	"S": this.getMilliseconds() 
	} 
	
	if (/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
	} 
	
	for (var k in o) { 
		if (new RegExp("(" + k + ")").test(format)) { 
		format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)); 
		} 
	} 
	return format; 
} 

/** 
*转换日期对象为日期字符串 
* @param date 日期对象 
* @param isFull 是否为完整的日期数据, 
* 为true时, 格式如"2000-03-05 01:05:04" 
* 为false时, 格式如 "2000-03-05" 
* @return 符合要求的日期字符串 
*/ 
function getSmpFormatDate(date, isFull) { 
	var pattern = ""; 
	if (isFull == true || isFull == undefined) { 
		pattern = "yyyy-MM-dd hh:mm:ss"; 
	} else { 
		pattern = "yyyy-MM-dd"; 
	} 
	return getFormatDate(date, pattern); 
} 

/** 
*转换当前日期对象为日期字符串 
* @param date 日期对象 
* @param isFull 是否为完整的日期数据, 
* 为true时, 格式如"2000-03-05 01:05:04" 
* 为false时, 格式如 "2000-03-05" 
* @return 符合要求的日期字符串 
*/ 
function getSmpFormatNowDate(isFull) { 
	return getSmpFormatDate(new Date(), isFull); 
} 

/** 
*转换long值为日期字符串 
* @param l long值 
* @param isFull 是否为完整的日期数据, 
* 为true时, 格式如"2000-03-05 01:05:04" 
* 为false时, 格式如 "2000-03-05" 
* @return 符合要求的日期字符串 
*/ 
function getSmpFormatDateByLong(l, isFull) { 
	return getSmpFormatDate(new Date(l), isFull); 
}

/** 
*转换long值为日期字符串 
* @param l long值 
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss 
* @return 符合要求的日期字符串 
*/ 
function getFormatDateByLong(l, pattern) {
	if(l!=""&&l!=null){
		return getFormatDate(new Date(l), pattern); 
	}else{
		return "";
	}
} 

/** 
*转换日期对象为日期字符串 
* @param l long值 
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss 
* @return 符合要求的日期字符串 
*/ 
function getFormatDate(date, pattern) { 
	if (date == undefined) { 
		date = new Date(); 
	} 
	if (pattern == undefined) { 
		pattern = "yyyy-MM-dd hh:mm:ss"; 
	}	 
	return date.format(pattern); 
} 

/**
 * 计算两个日期之间的天数
 * @param date1
 * @param date2
 * @returns 日期间的天数
 */
function dateDiff(date1, date2){       
    var type1 = typeof date1, type2 = typeof date2;       
    if(type1 == 'string')       
        date1 = stringToTime(date1);       
    else if(date1.getTime)       
        date1 = date1.getTime();       
    if(type2 == 'string')       
        date2 = stringToTime(date2);       
    else if(date2.getTime)       
        date2 = date2.getTime();   
    return (date2 - date1) / 1000 / 60 / 60 / 24;//除1000是毫秒，不加是秒   
}   
/**
 * 字符串转成Time(dateDiff)所需方法 
 * @param string
 * @returns 字符串转成的日期对象
 */ 
function stringToTime(string){       
    var f = string.split(' ', 2);       
    var d = (f[0] ? f[0] : '').split('-', 3);       
    var t = (f[1] ? f[1] : '').split(':', 3);       
    return (new Date(       
    parseInt(d[0], 10) || null,       
    (parseInt(d[1], 10) || 1)-1,       
    parseInt(d[2], 10) || null,       
    parseInt(t[0], 10) || null,      
    parseInt(t[1], 10) || null,       
    parseInt(t[2], 10) || null)).getTime();   
} 
