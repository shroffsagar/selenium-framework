var src=arguments[0],tgt=arguments[1];
var dataTransfer={
    dropEffect:'',effectAllowed:'all',files:[],items:{},types:[],
    setData:function(format,data){
             this.items[format]=data;
             this.types.append(format);
            },
    getData:function(format){
            return this.items[format];
    },
    clearData:function(format){}
};
var emit=function(event,target){
var evt=document.createEvent('Event');
evt.initEvent(event,true,false);
evt.dataTransfer=dataTransfer;
target.dispatchEvent(evt);
};
emit('dragstart',src);
emit('dragenter',tgt);
emit('dragover',tgt);
emit('drop',tgt);
emit('dragend',src);