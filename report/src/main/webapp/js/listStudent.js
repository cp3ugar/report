window.onload = function () {
    layui.use(['form', 'layedit', 'laydate','upload','table','layer'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate
            , $ = layui.jquery
            , table = layui.table
            , layer = layui.layer
            , upload = layui.upload;

        //添加学生
        $(document).on('click', '#addStu', function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-demo', //样式类名
                // closeBtn: 0, //不显示关闭按钮
                anim: 2,
                area: ['800px', '500px'],
                title: '添加学生',
                btn: ['确定', '取消'],
                shadeClose: false,
                content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">学号</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="studentId" type="text" name="username" lay-verify="required" lay-reqtext="学号是必填项，岂能为空？"\n' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">姓名</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input type="text" id="name" name="username" lay-verify="required" lay-reqtext="姓名是必填项，岂能为空？"\n' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">性别</label>\n' +
                    '        <div class="layui-input-block" id="sex">\n' +
                    '            <input type="radio" name="sex" value="男" title="男" checked="">\n' +
                    '            <input type="radio" name="sex" value="女" title="女">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">年龄</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="age" type="number" name="username" lay-verify="required" lay-reqtext="年龄是必填项，岂能为空？"\n' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">生日</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="birthday" name="date" id="birthday" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off"\n' +
                    '                   class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '</div></form>',
                yes: function(index, layero){
                    var studentId = $("#studentId").val();
                    var name = $("#name").val();
                    var sex = $('#sex input[name="sex"]:checked ').val();
                    var age = $("#age").val();
                    var birthday = $("#birthday").val();
                    $.ajax({
                        url: '/addStudent',
                        type: 'post',
                        data: {
                            "studentId": studentId,
                            "name": name,
                            "sex": sex,
                            "age": age,
                            "birthday": birthday
                        },
                        success: function (data) {
                            layer.msg(data.msg);
                            if("添加成功！"== data.msg){
                                layer.close(index);
                            }
                        }
                    })
                },
                btn2: function(index, layero){
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                },
            });
            form.render();
            //日期
            laydate.render({
                elem: '#birthday'
            });
        });

        //学生列表
        table.render({
            elem: '#stuList'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度
            , url: '/listStudent' //数据接口
            , method: 'get'
            ,page: {
                layout: ['prev', 'page', 'next','count','limit', 'skip'] //自定义分页布局
                ,groups: 3
            }
            , skin: 'line' //行边框风格
            , even: true //开启隔行背景
            , size: 'lg' //小尺寸的表格
            , toolbar: '#toolbarDemo'
            , defaultToolbar: ['filter', 'exports']
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', sort: true, fixed: 'left'}
                , {field: 'studentId', title: '学号'}
                , {field: 'name', title: '姓名', sort: true}
                , {field: 'sex', title: '性别'}
                , {field: 'age', title: '年龄'}
                , {field: 'birthday', title: '生日', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
        });

        //导出学生
        var exportStu = document.getElementById("exportStu");
        exportStu.onclick = function () {
            $.ajax({
                url: '/export',
                type: 'get',
                success: function (data) {

                }
            })
        }

        //导入学生
        upload.render({
            elem: '#importStu'
            ,url: '/import'
            ,accept: 'file' //普通文件
            ,done: function(res){
                console.log(res);
                layer.msg(res.msg);
            }
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

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });
    });
}