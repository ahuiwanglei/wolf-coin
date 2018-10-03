<#include "/webroot.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>

    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <link rel="shortcut icon" href="/favicon.ico">
    <link href="${webroot}/ligerUI/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <!-- 解决jQuery1.3.2和1.4.2的冲突  1.4.2 用Jquery  1.3.2 用 $ -->
    <script src="${webroot}/js/checkall.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="${webroot}/ligerUI/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${webroot}/css/button_defalut.css"/>
    <link href="${webroot}/css/css.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        $(function () {

            loadData();

            $('#add').bind('click', function () {
                show_depart(-1);
            });


            $('#serbut').bind('click', function () {
                loadData();
            });
        });

        function loadData() {
            //window['g'] =
            $("#maingrid_user").ligerGrid({
                checkbox: false,
                columns: [{
                    display: '币种',
                    name: 'symbol'
                    //render: formatMBUser
                }, {
                    display: '综合权重',
                    name: 'complex_weight'
                    //render: formatMBUser
                }, {
                    display: '交易量',
                    name: 'business_amount_rate'
                }, {
                    display: '买卖深度比',
                    name: 'market_depth_rate'
                }, {
                    display: '价格比',
                    name: 'price_rate'
                }, {
                    display: '时间',
                    name: 'create_at'
                }],
                dataAction: 'server',
                record: 'total',
                root: 'rows',
                pageSize: 10,
                pagesizeParmName: "rows",
                method: "POST",
                parms: {
                    searchkey: $('#searchkey').val()
                },
                url: '${webroot}/web/coin_weight/list/json'
            });
            $("#pageloading").hide();
        }


        function formatOption(rowdata, index, value) {
            var html = "";
            html += "<img src='${webroot}/resources/images/search.png'>&nbsp;";
            html += "<a style='cursor: pointer;' onclick='showUserDetail(" + value + ")'>查看</a>&nbsp;";
            html += "<img src='${webroot}/resources/images/010.gif'>&nbsp;";
            html += "<a style='cursor: pointer;' onclick='deleteUserInfo(" + value + ")'>删除</a>&nbsp;";
            html += "<img src='${webroot}/resources/images/037.gif'>&nbsp;";
            html += "<a style='cursor: pointer;' onclick='resetPassword(" + value + ")'>重置密码</a>&nbsp;";
            html += "<a style='cursor: pointer;' onclick='userTagList(" + value + ")'>标签</a>";
            return html;
        }

        function formatUserStatus(rowdata, index, value) {
            var STATUS = ["未激活", "已激活", "已删除"];
            if (value == 9) {
                return STATUS[2];
            } else {
                return STATUS[value];
            }
        }

    </script>
</head>
<body style="background: #FFFFFF !important; padding: 0px;">
<div class="RightBox" style="width: 98%;">
    <div class="list_title_bg1" style="margin-right: 10px;">
        <div class="list_title_bg1_ico">数据查询&nbsp;<span>&gt;&gt;</span>&nbsp;市场行情</div>
    </div>
    <div class="serach" style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
        <span class="serimg">&nbsp;</span>

        <div class="serbod">
            <table>
                <tr align="center">
                    <td><span>币种：</span></td>
                    <td>
                        <input type="text" id="searchkey"/>
                    </td>
                    <td> &nbsp;&nbsp;
                        <input name="serbut" type="submit" id="serbut" value=" ">

                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="list_info">
        <div class="bodtitle">
            <span class="tabimg"></span>
            <span class="tabfon">交易权重</span>
        </div>
            
        <div id="maingrid_user" style="margin-top:-10px; padding:0"></div>
    </div>
</div>
</body>
</html>