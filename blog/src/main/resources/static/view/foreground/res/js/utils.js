/**
 * 封装ajax方法
 * 默认POST
 * 数据格式传入json
 * next:代表执行后下一跳地址
 */
function ajax(opt) {
    opt = opt || {}; //opt以数组方式存参，如果参数不存在就为空。
    opt.method = opt.method || 'POST'; //转为大写失败，就转为POST
    opt.url = opt.url || ''; //检查URL是否为空
    opt.async = opt.async || true; //是否异步
    opt.data = opt.data || null; //是否发送参数，如POST、GET发送参数
    opt.next = opt.next || null;
    opt.success = opt.success || function () {
    }; //成功后处理方式
    opt.error = opt.error || function () {
    }; // 异常处理方式
    var xmlHttp = null; //定义1个空变量
    if (XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest(); //如果XMLHttpRequest存在就新建，IE大于9&&非IE有效
    } else {
        xmlHttp = new ActiveXObject('Microsoft.XMLHTTP'); //用于低版本IE
    }
    if (opt.method.toUpperCase() === 'POST') {
        xmlHttp.open(opt.method, opt.url, opt.async); //开始请求
        xmlHttp.setRequestHeader('Token', localStorage.getItem("token")); // token写入请求头
        xmlHttp.setRequestHeader('RequestType', "front"); // 请求类型:前端
        xmlHttp.setRequestHeader('x-requested-with', "XMLHttpRequest");
        xmlHttp.setRequestHeader('Content-Type', 'application/json;charset=utf-8'); //发送头信息，与表单里的一样。
        xmlHttp.send(opt.data); //发送POST数据
    } else if (opt.method.toUpperCase() === 'GET') {
        xmlHttp.open(opt.method, opt.url + '?' + opt.data, opt.async); //GET请求
        xmlHttp.send(null); //发送空数据
    }
    xmlHttp.onreadystatechange = function () {
         if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            // var flag = doResponse(xmlHttp.responseText, opt.data); //预处理
            // 如果客户端返回code正常
            // if (flag === true) {
            var obj = JSON.parse(xmlHttp.responseText);
            opt.success(obj);
            if (opt.next != null) { // 如果带有下一跳的信息则跳转
                window.location.href = opt.next;
            }
        }else if (xmlHttp.readyState == 4 &&xmlHttp.status == 403) {
             window.location.href = "/shop/ss/customer/index.html"
        } else{
            opt.error();
        }
    };
}

// formatObject
$.fn.formatObject = function () {
    var o = {};
    o["form_name"] = this.attr('name');
    var fd = {}; // field_data
    var a = this.serializeArray();
    $.each(a, function () {
        if (fd[this.name]) {
            if (!fd[this.name].push) {
                fd[this.name] = [fd[this.name]];
            }
            fd[this.name].push(this.value || '');
        } else {
            fd[this.name] = this.value || '';
        }
    });
    o["field_data"] = fd;
    return o;
}
// checkForm
$.fn.checkForm = function (opt) {
    $.each(opt, function (i, item) {
        if (item() == true) {
            simpleAlert("1")
        }
    });
}
// serializeObject
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
// String.format()
String.prototype.format = function () {
    a = this;
    for (k in arguments) {
        a = a.replace("{" + k + "}", arguments[k])
    }
    return a
}
// Date.Format()
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "y+": this.getYear(),
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[
            k]).substr(("" + o[k]).length)));
    return fmt;
}

// get yyyy-MM-dd
function getYMD() {
    return "yyyy-MM-dd";
}

// get China YMD
function getChinaYMD() {
    return "yyyy年MM月dd日"
}

// get fullYMD
function getFullYMD() {
    return "yyyy-MM-dd hh:mm:ss";
}

function getStanderTime() {
    return "yyyy-MM-ddThh:mm:ss"
}

// get fullChinaYMD
function getFullChinaYMD() {
    return "yyyy年MM月dd日 hh:mm:ss";
}

// get HourMin
function getHourMin() {
    return "hh:mm";
}

// get HourMinSec
function getHourMinSec() {
    return "hh:mm:ss";
}

//正则表达式格式化时间
function regexFormatTime(timeStr, formatStr) {
    var dataRegs = [/^(\d+)-(\d+)-(\d+)\s+(\d+)\:(\d+)\:(\d+)$/];
    // regex
    var time = "";
    time = new Date(timeStr);
    if (!time) {
        for (var i in dataRegs) {
            try {
                var arr = dataRegs[i].exec(timeStr);
                time = new Date(arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
                if (time) {
                    break;
                }
            } catch (e) {
                continue;
            }
        }
    }
    // format
    var formatTime = time.Format(formatStr);
    return formatTime;
}

//保留两位小数,自动补0
function returnFloat(value) {
    var value = Math.round(parseFloat(value) * 100) / 100;
    var xsd = value.toString().split(".");
    if (xsd.length == 1) {
        value = value.toString() + ".00";
        return value;
    }
    if (xsd.length > 1) {
        if (xsd[1].length < 2) {
            value = value.toString() + "0";
        }
        return value;
    }
}