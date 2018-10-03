<#include "webroot.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>企E家后台管理系统</title>
    <link href="${webroot}/ligerUI/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="${webroot}/ligerUI/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/indexdata.js" type="text/javascript"></script>
    <script type="text/javascript">
        var tab = null;
        var accordion = null;
        var tree = null;
        $(function ()
        {

            $("#current_date").html(getDateFormatValue(new Date()));

            $("#edit_pwd_btn").bind('click',function() {
                $.ligerDialog.open({
                    height:600,
                    width: 800,
                    title : '修改密码',
                    url: '${webroot}/manager/editpwd',
                    showMax: false,
                    showToggle: false,
                    showMin: false,
                    isResize: true,
                    slide: false
                });
            });
            //布局
            $("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });

            var height = $(".l-layout-center").height();

            //Tab
            $("#framecenter").ligerTab({ height: height });

            //面板
            $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

            $(".l-link").hover(function ()
            {
                $(this).addClass("l-link-over");
            }, function ()
            {
                $(this).removeClass("l-link-over");
            });
            //树
            $("#tree1").ligerTree({
                checkbox : false,
                url : '${webroot}/web/index/menutree',
                ajaxType : 'get',
                onError : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.responseText);
                },
                onSuccess : function(data) {
                    menu_data = data;
                },
                onSelect: function (node)
                {
                    if(node.data.url.replace(/(^\s+)|(\s+$)/g, "") == "") return;
                    if (!node.data.url) return;
                    var tabid = $(node.target).attr("tabid");
                    if (!tabid)
                    {
                        tabid = new Date().getTime();
                        $(node.target).attr("tabid", tabid)
                    }
                    if(node.data.url != "")
                        f_addTab(tabid, node.data.text, '${webroot}' + node.data.url);
                }
            });

            tab = $("#framecenter").ligerGetTabManager();
            accordion = $("#accordion1").ligerGetAccordionManager();
            tree = $("#tree1").ligerGetTreeManager();
            $("#pageloading").hide();

        });
        function f_heightChanged(options)
        {
            if (tab)
                tab.addHeight(options.diff);
            if (accordion && options.middleHeight - 24 > 0)
                accordion.setHeight(options.middleHeight - 24);
        }
        function f_addTab(tabid,text, url)
        {
            tab.addTabItem({ tabid : tabid,text: text, url: url });
        }

        function getDateFormatValue(date)
        {
            y=date.getFullYear();
            m=date.getMonth()+1;
            d=date.getDate();
            m=m<10?"0"+m:m;
            d=d<10?"0"+d:d;
            return y+"-"+m+"-"+d;
        }

    </script>
    <style type="text/css">
        body{ padding:0px; margin:0;   overflow:hidden;}
        .l-layout-top{background:#102A49; color:White;}
        .l-layout-bottom{ background:url('${webroot}/images/btbg.png'); text-align:center;}
        .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
        /* 顶部 */
        .l-topmenu{ margin:0; padding:0; height:43px; line-height:35px; background:url('${webroot}/images/top_bg.jpg') repeat-x bottom;  position:relative; border-top:1px solid #1D438B;  }
        .l-topmenu-logo{background:url('${webroot}/images/top_left.png') no-repeat;margin: 0px;width: 463px;height: 43px;}
        .l-topmenu-welcome{  position:absolute; right:0px; top:0px;color:#070A0C;}
        .top_right{background:url(${webroot}/images/top_right.jpg) no-repeat; width:312px; height:43px;}
        #top_exit{background-image: );height: 20px;width: 20px;position: absolute;top: 11px;right: 8px;cursor: pointer;}

    </style>
</head>
<body style="padding:0px;background:#EAEEF5;">
<div id="pageloading"></div>
<div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo"></div>
    <div class="l-topmenu-welcome"  >
        <div class="top_right"></div>
    </div>
    <a href="${webroot}/manager/admin/logout" id="logout_btn"><div id="top_exit" title="退出系统"></div></a>
</div>
<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; ">
    <div position="left"  title="主要菜单" id="accordion1">
        <div title="平台管理" class="l-scroll">
            <ul id="tree1" style="margin-top:3px;">
            </ul>
        </div>

    </div>
    <div position="center" id="framecenter">
        <div tabid="home" title="我的主页" style="height:300px" >
            <iframe frameborder="0" name="home" id="home" src="${webroot}/web/index/welcome"></iframe>
        </div>
    </div>
</div>
<div class="l-layout-bottom", style="height:27px; line-height:20px;">
    <span style="padding-left:20px; float:left">欢迎您：&nbsp;<#if Session.currentUser??>${Session.currentUser.nickname}</#if></span>
    <span style="float:right; padding-right:10px">系统工作时间：<span id="current_date"></span>&nbsp;&nbsp;&nbsp;&nbsp;管理平台&nbsp; V1.0版本</span>
</div>
<div style="display:none"></div>
</body>
</html>
