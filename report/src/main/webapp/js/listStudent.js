window.onload=function(){
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#birthday'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });

        //表单赋值
        layui.$('#LAY-component-form-setval').on('click', function(){
            form.val('example', {
                "username": "贤心" // "name": "value"
                ,"password": "123456"
                ,"interest": 1
                ,"like[write]": true //复选框选中状态
                ,"close": true //开关状态
                ,"sex": "女"
                ,"desc": "我爱 layui"
            });
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function(){
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

    });

    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;
        //选完文件后不自动上传
        upload.render({
            elem: '#stuFile'
            , url: '/import'
            , auto: false
            ,multiple: true
            ,accept: 'file'
            , bindAction: '#importStu'
            , done: function (res) {
                console.log(res);
                layer.alert(res.msg);
            }
        });
    })

    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#student'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度
            ,url: '/listStudent' //数据接口
            ,method:'get'
            ,page: true //开启分页
            ,skin: 'line' //行边框风格
            ,even: true //开启隔行背景
            ,size: 'lg' //小尺寸的表格
            ,toolbar: '#toolbarDemo'
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
                ,{field: 'studentId', title: '学号'}
                ,{field: 'name', title: '姓名',sort: true}
                ,{field: 'sex', title: '性别'}
                ,{field: 'age', title: '年龄'}
                ,{field: 'birthday', title: '生日', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            }
        });
    });

    var exportStu = document.getElementById("exportStu");
    exportStu.onclick = function(){
        $.ajax({
            url : '/export',
            type : 'get',
            success : function(data) {

            }
        })
    }

    var showAdd = document.getElementById("showAdd");
    showAdd.onclick = function(){
        $("#addTable").show();
        $("#showAdd").hide();
        $("#showImport").hide();
        $("#addStudent").show();
        $("#cancelAdd").show();
    }


    var addStudent = document.getElementById("addStudent");
    addStudent.onclick = function(){
        var studentId = $("#studentId").val();
        var name = $("#name").val();
        var sex = $("#sex option:selected").val();
        var age = $("#age").val();
        var birthday = $("#birthday").val();
        $.ajax({
            url : '/addStudent',
            type : 'post',
            data:{
                "studentId":studentId,
                "name":name,
                "sex":sex,
                "age":age,
                "birthday":birthday
            },
            success : function(data) {
                $("#studentId").val('');
                $("#name").val('');
                $("#sex").val('');
                $("#age").val('');
                $("#birthday").val('');
                alert(data.msg);
            }
        })
    }

    var cancelAdd = document.getElementById("cancelAdd");
    cancelAdd.onclick = function(){
        $("#showAdd").show();
        $("#showImport").show();
        $("#addStudent").hide();
        $("#cancelAdd").hide();
        $("#addTable").hide();
    }

    // var importStu = document.getElementById("importStu");
    // importStu.onclick = function(){
    //     var $file = $("#stuFile").val();//文件
    //     // 判断文件是否为空
    //     if ($file == "") {
    //         alert("请选择上传的目标文件! ")
    //         return false;
    //     }
    //     //判断文件类型
    //     var fileName = $file.substring($file.lastIndexOf(".") + 1).toLowerCase();
    //     if(fileName != "xls" && fileName !="xlsx"){
    //         alert("请选择Excel文件!");
    //         return false;
    //     }
    //     //判断文件大小
    //     var size = $("#stuFile")[0].files[0].size;
    //     if (size>104857600) {
    //         alert("上传文件不能大于100M!");
    //         return false;
    //     }
    //
    //     var formData = new FormData();
    //     formData.append("file",$('#stuFile')[0].files[0]);
    //     $.ajax({
    //         url : '/import',
    //         type : 'post',
    //         dataType:'json',
    //         data:formData,
    //         processData : false, // 使数据不做处理
    //         contentType : false, // 不要设置Content-Type请求头
    //         success : function(data) {
    //             alert(data.msg);
    //             // location.reload();
    //         }
    //     })
    // }
}