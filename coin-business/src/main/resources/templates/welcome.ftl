﻿<#include "/webroot.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script src="${webroot}/ligerUI/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
 <link href="${webroot}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
        $(f_init);

        function f_addTabItem(url, text)
        {
            var tabid = "ligerui" + new Date().toDateString();
            parent.f_addTab(tabid, text, url);
        }


        function f_init() {

            
            var v119 = [ 
                { tag: '表单', type: 'BUG', content: '修复isChecked方法重复调用的问题' },
                { tag: '表格', type: 'BUG', content: '修复表格下拉框/日期 编辑有时无法更新值的问题' },
                { tag: '表格', type: '需求', content: '分组行优化自定义函数' },
                { tag: '下拉框', type: 'BUG', content: '修复 调用updateStyle方法无法更新下拉框树的 问题' },
                { tag: '其他', type: '需求', content: '过滤器插件增加设置规则方法' }
            ];

            var v118 = [
                { tag: '基础', type: '需求', content: '增加条件过滤器插件(通用查询组件)' },
                { tag: '表单', type: '需求', content: '增强表单插件，支持自动创建表单，支持表单元素自定义控件的构建扩展。' },
                { tag: '表单', type: '需求', content: '文本框插件支持 整数、浮点数的限制' },
                { tag: '表格', type: 'BUG', content: '修复表格单元格编辑有时无法编辑的问题' },
                { tag: 'Tab', type: 'BUG', content: '修复以target方式增加时loading不消失的问题' },
                { tag: '综合', type: 'BUG', content: '修复如果dialog里面带有grid editor ，editor会被覆盖在下面的问题。' },
                { tag: '表格', type: 'BUG', content: '修复上移，下移方法 选择状态会被取消的问题。' },
                { tag: '表格', type: 'BUG', content: '修复不能多行编辑的bug' },
                { tag: '窗口', type: 'BUG', content: '修复在dialog再次显示dialog，最后显示的dialog会被遮住的问题' }
            ];


            var v117 = [
                { tag: '基础', type: '需求', content: '增加一套皮肤' },
                { tag: '表格', type: 'BUG', content: '修复grid重复加载数据的问题' },
                { tag: '表格', type: '优化', content: '修复loading最后才显示的问题' }, 
                { tag: '对话框', type: 'BUG', content: '修复ligerDialog.confirm() 和ligerMessageBox弹出的对话框位置不是屏幕居中的问题' },
                { tag: '对话框', type: 'BUG', content: '修复ligerDialog里面带有下拉框、或者日期选择框时，选择框被遮住的问题。' }, 
                { tag: '对话框', type: 'BUG', content: '修复ligerDialog里面带有下拉框、或者日期选择框时，选择框不会随着窗口移动而移动的问题。' }, 
                { tag: '表格', type: 'BUG', content: '优化grid 日期格式的自定义渲染函数，使之支持 /Date(XXXX)/ 这种格式' }, 
                { tag: '布局', type: '需求', content: 'tab 页签项，增加content和target参数。' }, 
                { tag: '表格', type: 'BUG', content: '修复奇偶行效果' },
                { tag: '布局', type: '需求', content: 'tab 增加 loading效果。' },
                 { tag: '布局', type: '需求', content: 'layout增加调整左/右侧宽度时的最小允许宽度' },
                 { tag: '表格', type: '需求', content: '支持改变行高和表头行高' },
                 { tag: '表单', type: '需求', content: '支持设定表单元素的宽度' },
                 { tag: '表格', type: '需求', content: '支持工具条.工具条支持图标图片' }
            ];


            var v116 = [
                { tag: '基础', type: '需求', content: '全面优化框架图片' },
                { tag: '基础', type: '需求', content: '整理简化皮肤Silvery,需要依赖默认的Aqua' },
                { tag: '表格', type: 'BUG', content: '修复 隐藏列后 再次加载数据，隐藏的列会再次显示 的问题' },
                { tag: '表格', type: 'BUG', content: '修复存在行序号时,汇总行错位的问题' },
                { tag: '窗口/对话框', type: '需求', content: '支持最小化、最大化、可收缩' },
                { tag: '对话框', type: '需求', content: '支持 右下角的提示框' },
                { tag: '表格', type: '需求', content: '重写列调整,需要依赖ligerResizable' },
                { tag: '表格', type: '需求', content: '支持动态设置columns' },
                { tag: '表格', type: '需求', content: '支持行移位、表头拖拽' },
                { tag: '树', type: '需求', content: '增加节点图标' },
                 { tag: '树', type: '需求', content: '增加节点拖拽' }, 
            ];

            var v115 = [
                { tag: '表格', type: '需求', content: '固定列' },
                { tag: '表格', type: '需求', content: '多表头支持跨行合并' },
                { tag: '表格', type: '需求', content: '增加行编辑模式' },
                { tag: '表格', type: '需求', content: '增加明细编辑模式' },
                { tag: '表格', type: '需求', content: '增加扩展接口：排序、格式化器,优化扩展接口：编辑器' },
                { tag: '表格', type: '需求', content: '选择行支持Ctrl选择' }, 
                { tag: '表格', type: '需求', content: '明细框可以设置高度(detail参数增加onExtend、onCollapse 和height)' },
                { tag: '表格', type: '需求', content: '参数移除renderDate、dblClickToEdit 方法移除stringToDate、getFormatDate' },
                { tag: '表格', type: '需求', content: '增加参数detailToEdit、frozen、frozenDetail、frozenCheckbox、detailHeight、rownumbers、frozenRownumbers、rownumbersColWidth；<BR>增加方法beginEdit、SubmitEdit、cancelEdit、reRender、addEditRow、extendDetail、collapseDetail、getSelectedRows(支持Ctrl)、getSelected、getSelecteds、getSelectedRowObjs；<BR>增加事件onBeforeChangeColumnWidth、onAfterChangeColumnWidth、onBeginEdit、onAfterBeginEdit、onCancelEdit、onBeforeSubmitEdit、onAfterSubmitEdit' }, 
                { tag: '表单', type: '需求', content: 'combobox 增加事件onToggle、onShow、onHide' }, 
                { tag: '树', type: 'BUG', content: '修复右键菜单不触发的问题' }
            ];

            var v114 = [
                { tag: '核心', type: '需求', content: '增加方法的调用方式：liger{Plugin}(method) ' },
                { tag: '核心', type: '需求', content: '增加获取属性的调用方式：liger{Plugin}("option",name)' },
                { tag: '核心', type: '需求', content: '增加设置属性的调用方式：liger{Plugin}("option",name,value)' },
                { tag: '综合', type: 'BUG', content: '修复V1.1.3部分插件没有触发事件的BUG' },
                { tag: '表单', type: 'BUG', content: '修复V1.1.3验证出现的气泡没有消失的BUG' },
                { tag: '布局', type: '需求', content: 'Tab控件增加双击关闭页签' },
                { tag: '布局', type: '需求', content: 'Tab控件增加拖动页签功能' }
            ];

            var v113 = [
                { tag: '核心', type: '需求', content: '引入core/base.js,每一个插件都需要依赖这个文件,其提供了ligerui内置事件处理机制、属性动态设置机制等等' },
                { tag: '核心', type: '需求', content: '每一个插件拥有get、set、bind、trigger的方法' },
                { tag: '基础', type: '优化', content: '优化了ligerResizable和ligerDrag' },
                { tag: '基础', type: '需求', content: 'ligerDrag增加副本模式' },
                { tag: '基础', type: '需求', content: 'ligerDrag增加指定区域释放拖动(Drop)的支持' }
            ];

            var v112 = [
                { tag: '综合', type: '需求', content: '给每一个插件提供onRender和onRendered接口' },
                { tag: '表格', type: '需求', content: '为表格编辑器提供扩展接口' },
                { tag: '表格', type: '需求', content: '参数onRClickToSelect(右击行时是否选中)改名whenRClickToSelect' },
                { tag: '表格', type: '需求', content: '增加参数clickToEdit、minColumnWidth' },
                { tag: '表格', type: '需求', content: '增加事件onEndEdit' },
                { tag: '表格', type: '需求', content: '增加方法setColumnWidth(调整列宽)' },
                { tag: '表格', type: '需求', content: '参数onRClickToSelect改名whenRClickToSelect' },
                { tag: '对话框', type: '优化', content: '移除ligerDialogImagePath,改为$.ligerui.DialogImagePath' },
                { tag: '对话框', type: '需求', content: '添加设置url方法' },
                { tag: '对话框', type: '需求', content: '增加加载完成事件onloaded' },
                { tag: '对话框', type: '需求', content: '增加参数：show、title' },
                { tag: 'Window', type: '需求', content: '添加load远程文件方法和设置url方法' },
                { tag: '气泡', type: '优化', content: '优化气泡控件' },
                { tag: '树', type: '需求', content: '增加方法arrayToTree' },
            ];
            
            var v111 = [
                 { tag: '综合', type: '需求', content: '给每一个组件管理器提供扩展接口' },
                { tag: '表格', type: '需求', content: '增加addRows方法,一次性增加多行,参数为行数据数组' },
                { tag: '表格', type: '需求', content: '增加了表格列初始化隐藏的属性hide' },
                { tag: '表格', type: 'BUG', content: '解决表格返回的数据为空时页面不反应的问题' },
                { tag: '表格', type: 'BUG', content: '解决表格隐藏列以后排序,依旧会把依把已经隐藏的数据显示出来的问题' },
                { tag: '表格', type: 'BUG', content: '解决在表头右键会出现【显示/隐藏列】，但是在表头点击 【显示/隐藏列】 不消失的问题' },
                { tag: '表格', type: 'BUG', content: '解决在表格的最后一列点击鼠标右键显示【显示/隐藏列】，【显示/隐藏列】错位的问题' },
                { tag: '表格', type: 'BUG', content: '解决在表格隐藏列以后，如果是明细情况，明细的宽度没有根据现有显示列改变的问题' },
                { tag: '表格', type: 'BUG', content: '解决调用显示/隐藏列后【显示/隐藏列菜单】没有更新的问题' },
                { tag: '表格', type: 'BUG', content: '解决树表格启用编辑状态时数据没有更新准确的问题' },
                { tag: '表格', type: 'BUG', content: '解决分组模式下启用明细出现问题的BUG' },
                { tag: '表格', type: 'BUG', content: '解决分组、明细展开框 显示时 没有右边框的问题' },
                { tag: '表格', type: 'BUG', content: '解决统计时存在 明细或者复选框 不能准确显示的问题' },
                { tag: '表格', type: 'BUG', content: '解决 分组显示时，收缩所有分组 不能准确显示的问题' },
                { tag: '表格', type: 'BUG', content: '解决 隐藏列后，调整表头宽度 不能准确显示的问题' },
                { tag: '下拉框', type: 'BUG', content: '解决带分页下拉框在调整grid宽度时没反应的问题' },
                { tag: '表单', type: 'BUG', content: '解决不能设置text长度。text本身是可以设置，但在form里初始化text时，没传参数的问题' },
                { tag: '表单', type: '需求', content: '统一为每一个表单元素"管理器"提供 设置只读、获取值、设置值的接口' }

            ];
            
            var v110 = [
                { tag: '表格', type: '需求', content: '加入分组模式' },
                { tag: '表格', type: '需求', content: '加入树模式' },
                { tag: '表格', type: '需求', content: '加入统计行(位于底部)' },
                { tag: '表格', type: '需求', content: '加入延时加载' },
                { tag: '表格', type: '需求', content: '加入本地查询支持' },
                { tag: '表格', type: '需求', content: '加入复选框初始化的支持' },
                { tag: '表格', type: '需求', content: '列 百分比' },
                { tag: '表格', type: '需求', content: '列 显示和隐藏' },
                { tag: '表格', type: '需求', content: '列 表头改变文字' },
                { tag: '表格', type: '需求', content: '编辑器事件: 编辑前事件、验证编辑器结果是否通过、结束编辑后事件' },
                { tag: '表格', type: '需求', content: '增加右击接口' },
                { tag: '表格', type: '需求', content: '多表头' },
                { tag: '表格', type: '优化', content: 'grid新增行的时候可通过column的defaultValue进行设置默认值' },
                { tag: '表格', type: 'BUG', content: 'grid的编辑功能和checkbox多选功能存在冲突' },
                { tag: '表格', type: 'BUG', content: '加载时不显示loading的问题，并加入自定义加载时的支持' },
                { tag: '表格', type: 'BUG', content: 'grid addRow()如果没有数据的时候row没有高度' },
                { tag: '表格', type: 'BUG', content: '修改可编辑grid中DateEditor编辑错误的问题' },
                { tag: '表格', type: 'BUG', content: '修改可编辑grid中DateEditor编辑错误的问题' },
                { tag: '下拉框', type: '需求', content: '支持动态改变值' },
                { tag: '下拉框', type: '需求', content: '增加打开下拉框前事件，利用这个参数可以用来调用其他函数，比如打开一个新窗口来选择值' },
                { tag: '表单', type: '需求', content: 'spinner支持最大最小值' },
                { tag: '下拉框', type: '需求', content: '支持动态改变值' },
                { tag: '树', type: '需求', content: '增加 选择/反选择节点 接口' },
                { tag: '弹窗', type: '需求', content: '支持回车关闭' },
                { tag: '弹窗', type: 'BUG', content: 'dialog.frame浏览器兼容支持' }
            ];
            var v102 = [
                { tag: '皮肤', type: '需求', content: '增加一套皮肤(Silvery)' },
                { tag: '皮肤', type: '需求', content: '增加一套图标' },
                { tag: '表单', type: '需求', content: '增加表单提示气泡插件，结合表单验证使用，在demo中提供表单验证的一个解决方案' },
                { tag: '表单', type: '优化', content: '优化表单,每个表单插件(ligerTextBox、ligerSpinner等)可以通过属性ligerui自动加载参数，如ligerui="{width:200}"' },
                { tag: '表格', type: '优化', content: 'gridRows和Total字段名可配置，提交给服务器的参数可配置，所有与用户操作交互的地方（如上一页、下一页）都加上事件"' },
                { tag: '表格', type: '优化', content: '插件执行直接返回ligerGrid Manager(其他用到接口管理对象的插件都类同)' },
                { tag: '树', type: '需求', content: '树支持id pid的这种线性数据结构格式' },
                { tag: '弹窗', type: 'BUG', content: '弹窗样式冲突' },
                { tag: '弹窗', type: '需求', content: '弹窗增加close支持' },
                { tag: '弹窗', type: '需求', content: '弹窗增加close支持' }
              ];

              var v101 = [
                { tag: '表格', type: '需求', content: '汇总方式增加一个render(自定义函数，不限于sum、avg、count、max、min)' },
                { tag: '表格', type: '需求', content: '增加复选框列，同时增加相应的事件和方法。' },
                { tag: '表格', type: '需求', content: '优化了显示速度' },
                { tag: '表格', type: 'BUG', content: '解决在IE6下表格头部文字消失的问题' },
                { tag: '表格', type: 'BUG', content: '解决包含在form下不能正常显示高度的问题(height设置为百分比)' },
                { tag: '弹窗', type: '需求', content: '增加新插件：弹框，命名空间为$.ligerDialog。一系列静态方法。包括Open、Success、Error、Warn、Show。（这个样式比较美观，于是加上去了。并采用九格的排版方式，兼容性更好，扩展性更好）集对话框、模态窗口、非模态窗口等于这个命名空间下。（原来已经有弹出框插件和窗口插件了，ligerWindow和ligerMessage，不推荐，暂时保留）' },
                { tag: '菜单', type: '需求', content: '增加新插件：菜单、菜单条、工具条,菜单可以自定义图片，可以动态设置项，动态的显示位置。菜单条和是在菜单的基础上显示的一个类似Window菜单的一个插件，工具条是一些按钮的集合，可以自定义图片。' },
                { tag: '树', type: '需求', content: '提供右击方法实现的接口' },
                { tag: '树', type: '优化', content: '点击项就折叠/反折叠，而不是点击 + 才折叠' },
                { tag: '下拉框', type: '需求', content: '支持在分页的表格在选取数据(配置grid,参数跟ligerGrid的参数一致)' },
                { tag: '下拉框', type: '需求', content: '提供Resize方法实现的接口' },
                { tag: '下拉框', type: '优化', content: '细节优化' },
                { tag: '布局', type: '需求', content: 'ligerTab增加右键菜单功能，包括 关闭其他/关闭全部等。' },
                { tag: '布局', type: '需求', content: 'ligerTab增加获取Items Count，删除项等常见方法 ' },
                { tag: '布局', type: '需求', content: 'ligerLayout增加初始化控制左边/右边隐藏、是否折叠、是否调整大小等方法。 ' },
                { tag: '布局', type: '优化', content: '面板点击项就折叠/反折叠，而不是点击 + 才折叠' },
                { tag: '布局', type: 'BUG', content: '解决ligerLayout，在IE6下调整Line太宽的问题 ' },
                { tag: '布局', type: 'BUG', content: '解决ligerLayout，包含在form下不能正常显示高度的问题 ' },
                { tag: '表单', type: 'BUG', content: 'ligerSpinner每次点击即增加/减少，而不是按住的时候才有效果 ' },
             ];
            

        }

        function f_addVersionLog(title, items)
        {
            var jtitle = $("<h2 class='l-title'></h2>");
            jtitle.html(title).appendTo('body');

            var tagGroups = [];
            var tagItems = [];
            $(items).each(function (i, item)
            {
                if (!item) return;
                var tag = item['tag'];
                var tagIndex = $.inArray(tag, tagGroups);
                if (tagIndex == -1)
                {
                    tagGroups.push(tag);
                    tagIndex = tagGroups.length - 1;
                    tagItems.push([]);
                }
                tagItems[tagIndex].push(item);
            });
            $(tagGroups).each(function (i, item)
            {
                f_addVersionTagLog(item, tagItems[i]);
            });
        }
        function f_addVersionTagLog(tag, items)
        {
            var jtitle = $("<h3 class='l-title'></h3>");
            jtitle.html(tag).appendTo('body');
            $(items).each(function ()
            {
                var jitem = $('<p class="l-log-content"><span class="l-log-content-tag"></span></p>');
                $("span:first", jitem).html("[" + this.type + "]");
                jitem.append(this.content).appendTo('body');
            });
        }

    </script>
    <style type="text/css">
        p.l-log-content
        {
            margin: 0;
            padding: 0;
            padding-left: 20px;
            line-height: 22px;
            font-size: 12px;
        }
        span.l-log-content-tag
        {
            color: #008000;
            margin-right: 2px;
            font-weight: bold;
        }
        h2.l-title
        {
            margin: 7px;
            padding: 0;
            font-size: 17px;
            font-weight: bold;
        }
        h3.l-title
        {
            margin: 4px;
            padding: 0;
            font-size: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body style="background: white; font-size: 12px;"> 
 <div class="RightBox" style="">
			<div style="height: 100px;">
				<div style="padding-left: 22px;padding-top: 20px;">
				 	
				</div>
			</div>
			<div style="margin: 0 auto; text-align: center">
				<img src="${webroot}/images/welcome.png" width="543" height="349" />
			</div>
		</div>
</body>
</html>
