window.onload = function () {
    layui.use(['form', 'layedit', 'laydate', 'upload', 'table', 'layer', 'element'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate
            , $ = layui.jquery
            , table = layui.table
            , upload = layui.upload
            , element = layui.element;

        //发起投票
        $(document).on('click', '#generateVote', function () {
            var i = 4;
            form.on('radio', function (data) {
                // console.log(data.elem); //得到radio原始DOM对象
                console.log(data.value); //被点击的radio的value值
                if (data.value == 2) {
                    $("#limitDiv").show()
                } else {
                    $("#limitDiv").hide()
                }
            });
            layer.open({
                type: 1,
                skin: 'layui-layer-demo', //样式类名
                // closeBtn: 0, //不显示关闭按钮
                anim: 2,
                area: ['800px', '500px'],
                title: '发起投票',
                btn: ['添加选项', '确定', '取消'],
                shadeClose: false,
                content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">主题</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="title" type="text" name="title" lay-verify="required"' +
                    '                   placeholder="请填写投票主题" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">类型</label>\n' +
                    '        <div id="type" class="layui-input-block" lay-verify="required">\n' +
                    '           <input type="radio" name="type" value="1" title="单选" checked>' +
                    '           <input type="radio" name="type" value="2" title="多选">' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div id="limitDiv" hidden class="layui-form-item">\n' +
                    '        <label class="layui-form-label">多选个数</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="limit" type="number" name="limit" lay-verify="required"' +
                    '                   placeholder="多选最多选几个" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>' +
                    '    <div id="" class="layui-form-item">\n' +
                    '        <label class="layui-form-label">投票总人数</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="amount" type="number" name="amount" lay-verify="required"' +
                    '                   placeholder="投票总人数" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>' +
                    '    <div id="options" class="layui-form-item">\n' +
                    '        <label class="layui-form-label">选项</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input type="text" name="option" lay-verify="required"' +
                    '                   placeholder="选项1" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input type="text" name="option" lay-verify="required"' +
                    '                   placeholder="选项2" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input type="text" name="option" lay-verify="required"' +
                    '                   placeholder="选项3" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div id="" class="layui-form-item">\n' +
                    '        <label class="layui-form-label">截止时间</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="deadline" type="text" name="deadline" lay-verify="required"' +
                    '                   placeholder="截止时间" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>' +
                    '</div></form>',
                btn1: function (index, layero) {
                    var content = '<div class="layui-input-block">\n' +
                        '            <input type="text" name="option" lay-verify="required"' +
                        '                   placeholder="选项' + i + '" autocomplete="off" class="layui-input">\n' +
                        '        </div>';
                    if (i <= 10) {
                        $("#options").append(content);
                        i++;
                    } else {
                        layer.msg("选项最多10个");
                    }
                },
                btn2: function (index, layero) {
                    // console.log($("#userId").val());
                    var param = {};
                    if ($("#title").val() == "") {
                        layer.msg("请输入主题");
                        return;
                    }
                    if ($("#amount").val() == "") {
                        layer.msg("请输入总人数");
                        return;
                    }
                    if ($("input[name='option']").val() == "") {
                        layer.msg("请输入选项值");
                        return;
                    }
                    param.title = $("#title").val();
                    param.type = $('#type input[name="type"]:checked').val();
                    param.userId = $("#userId").val();
                    param.limit = $("#limit").val();
                    param.amount = $("#amount").val();
                    param.deadline = $("#deadline").val();
                    var arr = document.getElementsByName("option");
                    var options = new Array();
                    for (var item of arr) {
                        options.push({id: "", name: item.value, voteId: ""});
                    }
                    param.options = options;
                    // console.log(param);
                    $.ajax({
                        url: '/generateVote',
                        type: 'post',
                        data: JSON.stringify(param),
                        dataType: 'json',
                        contentType: 'application/json;charset=utf-8',
                        success: function (data) {
                            console.log(data);
                            if (data.code == 0) {
                                layer.msg(data.msg);
                                layer.close(index);
                                setTimeout('window.location.reload()', 500);
                            } else {
                                layer.msg(data.msg);
                            }
                        }
                    })
                },
                btn3: function (index, layero) {
                    layer.close(index);
                },
            });
            form.render();
            //日期
            laydate.render({
                elem: '#deadline'
                ,type: 'datetime'
            });
        });

        //投票列表
        table.render({
            id: 'voteList'
            , elem: '#voteList'
            , cellMinWidth: 80
            , url: '/listVote'
            , method: 'get'
            , page: {
                layout: ['prev', 'page', 'next', 'count', 'limit', 'skip']
                , groups: 3
            }
            , skin: 'line' //行边框风格
            , even: true //开启隔行背景
            , size: 'lg' //小尺寸的表格
            , toolbar: '#toolbarDemo'
            , defaultToolbar: ['filter', 'exports']
            , cols: [[ //表头
                {type: 'radio', fixed: 'left'}
                , {field: 'title', title: '主题'}
                , {field: 'amount', title: '投票总数', sort: true}
                , {field: 'number', title: '已投票数'}
            ]]
        });

        //查看投票 && 投票
        $(document).on('click', '#detailVote', function () {
            var checkStatus = table.checkStatus('voteList');
            // console.log(checkStatus);
            var data = checkStatus.data[0]; //获取选中行的数据
            if (!data) {
                layer.msg("请选择要查看的投票");
                return;
            }
            // console.log(data);
            layer.open({
                type: 1,
                skin: 'layui-layer-demo', //样式类名
                // closeBtn: 0, //不显示关闭按钮
                anim: 2,
                edit: 'text',
                area: ['800px', '500px'],
                title: '查看投票',
                btn: ['投票', '关闭'],
                shadeClose: false,
                content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                    '    <div id="" class="layui-form-item">\n' +
                    '        <label class="layui-form-label">倒计时</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input disabled id="countdown" type="text" name="countdown" lay-verify="required"' +
                    '              style="color: #FF5722;"  autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>' +
                    '    <div id="" class="layui-form-item">\n' +
                    '        <label class="layui-form-label">截止时间</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input disabled id="deadline" type="text" name="deadline" lay-verify="required"' +
                    '                   placeholder="截止时间" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">主题</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input disabled id="title" type="text" name="title" lay-verify="required"' +
                    '                   placeholder="请填写投票主题" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div id="options" class="layui-form-item">\n' +
                    '        <label class="layui-form-label">选项</label>\n' +
                    '        <div id="optionType" class="layui-input-block" lay-verify="required">' +
                    '         </div>' +
                    '    </div>\n' +
                    '    <div id="resultDiv" hidden class="layui-form-item">\n' +
                    '        <label class="layui-form-label">投票结果</label>' +
                    '        <div id="result" class="layui-input-block">\n' +
                    '        </div>' +
                    '    </div>' +
                    '</div></form>',
                btn1: function (index, layero) {
                    var param = {};
                    param.id = data.id;
                    param.userId = $("#userId").val();
                    param.optionId = $('#optionType input[name="option"]:checked ').val();
                    var options = new Array();
                    $("input:checkbox[name='option']:checked").each(function (i) {
                        options[i] = {id: "", name: $(this).val(), voteId: ""}
                    });
                    param.options = options;
                    $.ajax({
                        url: '/vote',
                        type: 'post',
                        data: JSON.stringify(param),
                        dataType: 'json',
                        contentType: 'application/json;charset=utf-8',
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.msg);
                                var vote = data.data;
                                $("#resultDiv").show();
                                var colorArr = ["red", "orange", "green", "blue", "cyan", "black", "grey", "red", "orange", "green"];
                                var total = 0;
                                for (var i = 0; i < vote.options.length; i++) {
                                    if (vote.voteData[vote.options[i].id] != null) {
                                        total += vote.voteData[vote.options[i].id].length;
                                    }
                                }
                                for (var i = 0; i < vote.options.length; i++) {
                                    var rate;
                                    var number;
                                    if (vote.voteData[vote.options[i].id] != null) {
                                        number = vote.voteData[vote.options[i].id].length;
                                        rate = parseInt((number / total) * 100);
                                    } else {
                                        number = 0;
                                        rate = 0;
                                    }
                                    $("#result").append('<div  class="layui-input">' +
                                        '<label>' + vote.options[i].name + ":" + '</label>' +
                                        '<label>' + number + "人" + '</label>' +
                                        '<div class="layui-progress" layui-progress-big lay-filter="demo" lay-showPercent="yes">\n' +
                                        '  <div class="layui-progress-bar layui-bg-' + colorArr[i] + '" lay-percent="' + rate + '%"></div>\n' +
                                        '</div>' +
                                        '</div>');

                                }
                                form.render();
                                element.render('progress');
                                // element.progress('demo', '80%');
                            } else {
                                layer.msg(data.msg);
                            }
                        }
                    })
                },
                btn2: function (index, layero) {
                    layer.close(index);
                },
            });
            $.ajax({
                url: '/getVote/' + data.id,
                type: 'get',
                success: function (data) {
                    if (data.code == 0) {
                        var vote = data.data;
                        $("#title").val(vote.title);
                        $("#deadline").val(vote.deadline);
                        getTime();
                        for (var i = 0; i < vote.options.length; i++) {
                            $("#optionType").append('<input type="' + (vote.type == 1 ? 'radio' : 'checkbox') + '" name="option" ' +
                                'value="' + vote.options[i].id + '" ' +
                                'title="' + vote.options[i].name + '">');
                        }
                        form.render();
                    } else {
                        layer.msg(data.msg);
                    }
                }
            })
            form.render();

        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
            ;
        });
    })

    function getTime() {
        countDown();

        function addZero(i) {
            return i < 10 ? "0" + i : i + "";
        }

        function countDown() {
            var nowtime = new Date();
            var endtime = new Date($("#deadline").val());
            var lefttime = parseInt((endtime.getTime() - nowtime.getTime()) / 1000);
            var d = parseInt(lefttime / (24 * 60 * 60))
            var h = parseInt(lefttime / (60 * 60) % 24);
            var m = parseInt(lefttime / 60 % 60);
            var s = parseInt(lefttime % 60);
            d = addZero(d)
            h = addZero(h);
            m = addZero(m);
            s = addZero(s);
            var res = d+"天"+h+"时"+m+"分"+s+"秒";
            if(lefttime <= 0){
                res = "投票已结束";
            }
            $("#countdown").val(res);
            setTimeout(countDown, 1000);
        }
    }
}