export default [{
    name: '基础',
    id: 'basic',
    sub: [{
        name: '列表功能',
        componentName: 'List'
    }, {
        name: 'Container 布局容器',
        componentName: 'BasicContainer'
    }]
}, 

{
    name: 'Form',
    id: 'form',
    sub: [{
        name: 'Radio 单选框',
        componentName: 'FormRadio'
    }, {
        name: 'Checkbox 多选框',
        componentName: 'FormCheckbox'
    }]
},

{
    name: '菜单1',
    id: 'menu1',
    sub: [{
        name: '子菜单1', 
    }, {
        name: '子菜单2',
        componentName: 'menu1-2'
    }]
},

];