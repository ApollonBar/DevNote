/******************************************��ʾ����Ƶ�� newsChannel.js*********************************************/
/********************************* ��Ȩ���� BLOGDRIVER  ����:Թ��Թ��**********************************************/
  
   /*����RSS��XML�ļ�*/
   function writeChannel(){
   	var bV=parseInt(navigator.appVersion);       //�õ�������İ汾
	IE4=((document.all)&&(bV>=4))?true:false;    //��֤������Ƿ�ΪIE4���ϰ汾
   	if(IE4){
   	    if(Channel.num2==0){
   	    	document.all.Channel.innerHTML+="RSS������...";
   	    }
   	    Channel.startDate=new Date();            
  	    var xmlSource = new ActiveXObject("Msxml2.DOMDocument");  //����XMLDOM����;
  	    Channel.xmlSource=xmlSource;
  	    /*�첽��ȡXML�ļ�*/  	    	                     
  	    xmlSource.resolveExternals = false;                   
  	    xmlSource.onreadystatechange =Channel.createHtml; 
  	    xmlSource.load(Channel.xmlPath[Channel.num2]);	  	         	
  	}
  	else{
  	    document.write("�Բ���,��������ʱ��֧�ָ������");
  	}
   }
   
   /*ͨ��XML�ļ�����HTML����*/
   function createHtml(){   	      	 	 
	 var startTime=new Date();
	 var newsName;      //Ƶ������·��
	 var newsLike;      //Ƶ�������ӵ�ַ·��
	 var newsItem;      //Ƶ����Ŀ��·��
	 var newsItemName;  //Ƶ����Ŀ���Ƶ�·��
	 var newsItemLike;  //Ƶ����Ŀ���ӵ�·��   
    	 var str=""; 
    	 if (Channel.xmlSource.readyState == 4){
    	      var err = Channel.xmlSource.parseError;    	        	      
    	      if (err.errorCode == 0){   
    	      	if(Channel.num2==0){
    	      	   var re = /RSS������.../g; 
    	      	   document.all.Channel.innerHTML=document.all.Channel.innerHTML.replace(re,"");
    	      	}
    	      	/*ѡ��RSS��Ӧ�Ĺ���*/    	      	
	    	if(Channel.rssVersion[Channel.num2]=="rss1"||Channel.rssVersion[Channel.num2]=="RSS1"){
		      newsName="/rdf:RDF/channel/title";
		      newsLike="/rdf:RDF/channel/link";
		      newsItem="/rdf:RDF/item";      
		      newsItemName="title";
		      newsItemLike="link";	      
		   }	   
		   else if(Channel.rssVersion[Channel.num2]=="rss2"||Channel.rssVersion[Channel.num2]=="RSS2"){
		      newsName="/rss/channel/title";
		      newsLike="/rss/channel/link";
		      newsItem="/rss/channel/item";      
		      newsItemName="title";
		      newsItemLike="link";
		   }
		   else if(Channel.rssVersion[Channel.num2]=="atom"||Channel.rssVersion[Channel.num2]=="ATOM"){
		      newsName="/feed/title";
		      newsLike="/feed/generator";
		      newsItem="/feed/entry";      
		      newsItemName="title";
		      newsItemLike="link";
		   }
		   else{
		      document.all.Channel.innerHTML+="�޷�������RSS�ĸ�ʽ";
		      return;
		   }
		   /*�õ�Ƶ�����ƺ����ӵ�ַ*/
		   var node=Channel.xmlSource.selectSingleNode(newsName);  
		   var blogName="";
		   var blogLike="";	
		   if(node!=null){
		      var blogName=node.text;
		   }
		   node=Channel.xmlSource.selectSingleNode(newsLike);   
		   if(node!=null){
		      var blogLike=node.text;
		   }
		   if(blogName!=""&&blogLike!=""){
		      str+="<DIV class='rss'><span class='rssTitle'>"+blogName+"</span><ul>";
		   }
		   /*�õ�ָ������������������ƺ����ӵ�ַ*/
		   var nodes=Channel.xmlSource.selectNodes(newsItem);
		   var j=0;	        
		   for(i=0;i<nodes.length;i++){	     
		   	var ItemName;
		   	var ItemLike;
		   	if(Channel.rssVersion=="atom"){
		   	    ItemName=nodes.item(i).getElementsByTagName(newsItemName).item(0).text
		   	    ItemLike=nodes.item(i).getElementsByTagName(newsItemLike).item(0).getAttribute("href");
		   	}
		   	else{
		   	   ItemName=nodes.item(i).getElementsByTagName(newsItemName).item(0).text;
		   	   ItemLike=nodes.item(i).getElementsByTagName(newsItemLike).item(0).text;
		   	}
		   	str+="<li class='newsItem'><a href="+ItemLike+" target='_blank'>"+ItemName+"</a></li>";	   
		   	if(j==Channel.count[Channel.num2]){
		   		break;
		   	}
		   	j++;
		   }
		   if(blogName!=""&&blogLike!=""){	   
		        var endTime=new Date();	   
		   	str+="</ul><span class='loadTime'>����ʱ��:"+(endTime-Channel.startDate)/1000+"��</span></DIV>";	   
		   }
		   document.all.Channel.innerHTML+=str;
		   Channel.num2++;
		   if(Channel.num2<Channel.num){
		      writeChannel();
		   }
		   else{
		       Channel.xmlPath=null;
		       Channel.count=null;
		       Channel.rssVersion=null;
		       Channel.xmlSource=null;
		       Channel.num=0;		       
   		       Channel.num2=0;  
		   }
	    }
	}
    }    
        
   /*����Ƶ�������캯��*/
   function Channel(xmlPath,count,rssVersion){   	
   	Channel.xmlPath[Channel.num]=xmlPath;
   	Channel.count[Channel.num]=count;   	
   	Channel.rssVersion[Channel.num]=rssVersion;
    	Channel.num++;
   }
      
   Channel.xmlPath=new Array();    // ����XML��ַ������
   Channel.count=new Array();      // ������Ҫ��ʾ������Ŀ����������
   Channel.rssVersion=new Array(); // ����ʹ�ú���RSS�ı�ʶ������
   Channel.xmlSource;              // XMLDOM����
   Channel.startDate;              // ��ʼ����ʱ��
   Channel.num=0;                  // Ƶ���ܸ���
   Channel.num2=0;                 // ��ǰ���ɵ�Ƶ����
   Channel.writeChannel=writeChannel; 
   Channel.createHtml=createHtml;
   
   
   
   