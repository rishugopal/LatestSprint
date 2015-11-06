
var req;
var active;
var res;
$(document).ready(function(){
		var iDiv = document.createElement('div');
		var iDiv2 = document.createElement('div');
		/*var iDiv3 = document.createElement('div');*/
		iDiv2.id='chat_div';
	/*	iDiv3.id='log';*/
		iDiv.id = 'dialog-confirm';
	iDiv.title='Do you need help';
	document.getElementsByTagName('body')[0].appendChild(iDiv);
	document.getElementsByTagName('body')[0].appendChild(iDiv2);
	/*document.getElementsByTagName('body')[0].appendChild(iDiv3);*/
	
	$(document).on('click', 'label', function () {
		
		var getProperty = function (propertyName) {
		    return obj[propertyName];
		};

		url=getProperty(this.id);
		console.log(url);
		window.alert(url);
		
		});
	("body").click(function() {
		var getProperty = function (propertyName) {
		    return obj[propertyName];
		};
		   // do something here
		console.log($(event.target).text());
		url=getProperty($(event.target).text());
		if(url!=null && url!="NoResource" && url!="undefined"){
		console.log(url);
		window.alert(url);}
		});
		
		
	});
	$(document).on('click', 'input', function () {
		/*if (gOptions.enabled) {
      	    for (var i = 0; i < gOptions.count; i++) {
      	    	if(this.id=gOptions.id){
      	    	var URL =  gOptions.temp;
      	    	var id1=this.id;
      	    }}
      	  };*/
		
		/*var url=onFoucsTextField(this.id);*/
		var getProperty = function (propertyName) {
		    return obj[propertyName];
		};

		url=getProperty(this.id);
		console.log(url);
		
	if(url!=null && url!="NoResource"){
		console.log("i am in");
  $( "#dialog-confirm" ).dialog({
	  
    resizable: false,
    height:140,
    modal: true,
    buttons: {
      "Play Video": function() {
    	  
      	var strWindowFeatures = "location=yes,height=570,width=520,scrollbars=yes,status=yes";
      	    	var win = window.open(url, "_blank", strWindowFeatures);
      	    	
      	    	$( this ).dialog( "close" );
      	
      },
      "Chat": function() {
      	/*var strWindowFeatures = "location=yes,height=570,width=520,scrollbars=yes,status=yes";
      	var URL = "chatbox.html"
      	var win =window.open(URL, "_blank", strWindowFeatures);*/
        getchat();
    	  $( this ).dialog( "close" );
      },
      "Click to call": function() {
      	this.src="https://www.linkedin.com/cws/share?mini=true&amp;url=" + location.href;
      	window.alert("Kindly call 43XXXXX763");
          $( this ).dialog( "close" );
        },
    }
  }); }
}); 
function onFoucsTextField(name)
{
	console.log("Processing javascript");
	if(window.XMLHttpRequest)
		{
		req=new XMLHttpRequest();
		}
	else if(window.ActiveXObject)
		{
		req=new ActiveXObject("Microsoft.XMLHTTP");
		}
	//active=document.activeElement.name;
	var url="SuggestServlet?name="+name;
	
	req.onreadystatechange=function(){
		
		if(req.readyState==4 && req.status==200){
			//document.getElementById("product_suggest").innerHTML=req.responseText;
			console.log("changed value");
		console.log(req.responseText);
			res=req.responseText;
					}
	};
	req.open("GET",url,true);
	req.send();
	return res;
	
}
function getResponse()
{
	console.log("getting respose");
if(req.readyState==4)
	{
	if(req.status==200)
		{
		console.log("found it");
		document.getElementById("product_suggest").innerHTML=req.responseText;
		var link=req.responseText;
		return link;
		}
	}

}

			/*
				declare gloabl box variable,
				so we can check if box is alreay open,
				when user click toggle button
			*/
			var box = null;
			
			/*
				we are now adding click hanlder for 
				toggle button.
			*/
			// TODO: implement destroy()
			(function($) {
			    $.widget("ui.chatbox", {
			        options: {
			            id: null, //id for the DOM element
			            title: null, // title of the chatbox
			            user: null, // can be anything associated with this chatbox
			            hidden: false,
			            offset: 0, // relative to right edge of the browser window
			            width: 300, // width of the chatbox
			            messageSent: function(id, user, msg) {
			                // override this
			                this.boxManager.addMsg(user.first_name, msg);
			            },
			            boxClosed: function(id) {
			            }, // called when the close icon is clicked
			            boxManager: {
			                // thanks to the widget factory facility
			                // similar to http://alexsexton.com/?p=51
			                init: function(elem) {
			                    this.elem = elem;
			                },
			                addMsg: function(peer, msg) {
			                    var self = this;
			                    var box = self.elem.uiChatboxLog;
			                    var e = document.createElement('div');
			                    box.append(e);
			                    $(e).hide();

			                    var systemMessage = false;

			                    if (peer) {
			                        var peerName = document.createElement("b");
			                        $(peerName).text(peer + ": ");
			                        e.appendChild(peerName);
			                    } else {
			                        systemMessage = true;
			                    }

			                    var msgElement = document.createElement(
			                        systemMessage ? "i" : "span");
			                    $(msgElement).text(msg);
			                    e.appendChild(msgElement);
			                    $(e).addClass("ui-chatbox-msg");
			                    $(e).css("maxWidth", $(box).width());
			                    $(e).fadeIn();
			                    self._scrollToBottom();

			                    if (!self.elem.uiChatboxTitlebar.hasClass("ui-state-focus")
			                        && !self.highlightLock) {
			                        self.highlightLock = true;
			                        self.highlightBox();
			                    }
			                },
			                highlightBox: function() {
			                    var self = this;
			                    self.elem.uiChatboxTitlebar.effect("highlight", {}, 300);
			                    self.elem.uiChatbox.effect("bounce", {times: 3}, 300, function() {
			                        self.highlightLock = false;
			                        self._scrollToBottom();
			                    });
			                },
			                toggleBox: function() {
			                    this.elem.uiChatbox.toggle();
			                },
			                _scrollToBottom: function() {
			                    var box = this.elem.uiChatboxLog;
			                    box.scrollTop(box.get(0).scrollHeight);
			                }
			            }
			        },
			        toggleContent: function(event) {
			            this.uiChatboxContent.toggle();
			            if (this.uiChatboxContent.is(":visible")) {
			                this.uiChatboxInputBox.focus();
			            }
			        },
			        widget: function() {
			            return this.uiChatbox
			        },
			        _create: function() {
			            var self = this,
			            options = self.options,
			            title = options.title || "No Title",
			            // chatbox
			            uiChatbox = (self.uiChatbox = $('<div></div>'))
			                .appendTo(document.body)
			                .addClass('ui-widget ' +
			                          'ui-corner-top ' +
			                          'ui-chatbox'
			                         )
			                .attr('outline', 0)
			                .focusin(function() {
			                    // ui-state-highlight is not really helpful here
			                    //self.uiChatbox.removeClass('ui-state-highlight');
			                    self.uiChatboxTitlebar.addClass('ui-state-focus');
			                })
			                .focusout(function() {
			                    self.uiChatboxTitlebar.removeClass('ui-state-focus');
			                }),
			            // titlebar
			            uiChatboxTitlebar = (self.uiChatboxTitlebar = $('<div></div>'))
			                .addClass('ui-widget-header ' +
			                          'ui-corner-top ' +
			                          'ui-chatbox-titlebar ' +
			                          'ui-dialog-header' // take advantage of dialog header style
			                         )
			                .click(function(event) {
			                    self.toggleContent(event);
			                })
			                .appendTo(uiChatbox),
			            uiChatboxTitle = (self.uiChatboxTitle = $('<span></span>'))
			                .html(title)
			                .appendTo(uiChatboxTitlebar),
			            uiChatboxTitlebarClose = (self.uiChatboxTitlebarClose = $('<a href="#"></a>'))
			                .addClass('ui-corner-all ' +
			                          'ui-chatbox-icon '
			                         )
			                .attr('role', 'button')
			                .hover(function() { uiChatboxTitlebarClose.addClass('ui-state-hover'); },
			                       function() { uiChatboxTitlebarClose.removeClass('ui-state-hover'); })
			                .click(function(event) {
			                    uiChatbox.hide();
			                    self.options.boxClosed(self.options.id);
			                    return false;
			                })
			                .appendTo(uiChatboxTitlebar),
			            uiChatboxTitlebarCloseText = $('<span></span>')
			                .addClass('ui-icon ' +
			                          'ui-icon-closethick')
			                .text('close')
			                .appendTo(uiChatboxTitlebarClose),
			            uiChatboxTitlebarMinimize = (self.uiChatboxTitlebarMinimize = $('<a href="#"></a>'))
			                .addClass('ui-corner-all ' +
			                          'ui-chatbox-icon'
			                         )
			                .attr('role', 'button')
			                .hover(function() { uiChatboxTitlebarMinimize.addClass('ui-state-hover'); },
			                       function() { uiChatboxTitlebarMinimize.removeClass('ui-state-hover'); })
			                .click(function(event) {
			                    self.toggleContent(event);
			                    return false;
			                })
			                .appendTo(uiChatboxTitlebar),
			            uiChatboxTitlebarMinimizeText = $('<span></span>')
			                .addClass('ui-icon ' +
			                          'ui-icon-minusthick')
			                .text('minimize')
			                .appendTo(uiChatboxTitlebarMinimize),
			            // content
			            uiChatboxContent = (self.uiChatboxContent = $('<div></div>'))
			                .addClass('ui-widget-content ' +
			                          'ui-chatbox-content '
			                         )
			                .appendTo(uiChatbox),
			            uiChatboxLog = (self.uiChatboxLog = self.element)
			                .addClass('ui-widget-content ' +
			                          'ui-chatbox-log'
			                         )
			                .appendTo(uiChatboxContent),
			            uiChatboxInput = (self.uiChatboxInput = $('<div></div>'))
			                .addClass('ui-widget-content ' +
			                          'ui-chatbox-input'
			                         )
			                .click(function(event) {
			                    // anything?
			                })
			                .appendTo(uiChatboxContent),
			            uiChatboxInputBox = (self.uiChatboxInputBox = $('<textarea></textarea>'))
			                .addClass('ui-widget-content ' +
			                          'ui-chatbox-input-box ' +
			                          'ui-corner-all'
			                         )
			                .appendTo(uiChatboxInput)
			                .keydown(function(event) {
			                    if (event.keyCode && event.keyCode == $.ui.keyCode.ENTER) {
			                        msg = $.trim($(this).val());
			                        if (msg.length > 0) {
			                            self.options.messageSent(self.options.id, self.options.user, msg);
			                        }
			                        $(this).val('');
			                        return false;
			                    }
			                })
			                .focusin(function() {
			                    uiChatboxInputBox.addClass('ui-chatbox-input-focus');
			                    var box = $(this).parent().prev();
			                    box.scrollTop(box.get(0).scrollHeight);
			                })
			                .focusout(function() {
			                    uiChatboxInputBox.removeClass('ui-chatbox-input-focus');
			                });

			            // disable selection
			            uiChatboxTitlebar.find('*').add(uiChatboxTitlebar).disableSelection();

			            // switch focus to input box when whatever clicked
			            uiChatboxContent.children().click(function() {
			                // click on any children, set focus on input box
			                self.uiChatboxInputBox.focus();
			            });

			            self._setWidth(self.options.width);
			            self._position(self.options.offset);

			            self.options.boxManager.init(self);

			            if (!self.options.hidden) {
			                uiChatbox.show();
			            }
			        },
			        _setOption: function(option, value) {
			            if (value != null) {
			                switch (option) {
			                case "hidden":
			                    if (value)
			                        this.uiChatbox.hide();
			                    else
			                        this.uiChatbox.show();
			                    break;
			                case "offset":
			                    this._position(value);
			                    break;
			                case "width":
			                    this._setWidth(value);
			                    break;
			                }
			            }
			            $.Widget.prototype._setOption.apply(this, arguments);
			        },
			        _setWidth: function(width) {
			            this.uiChatboxTitlebar.width(width + "px");
			            this.uiChatboxLog.width(width + "px");
			            this.uiChatboxInput.css("maxWidth", width + "px");
			            // padding:2, boarder:2, margin:5
			            this.uiChatboxInputBox.css("width", (width - 18) + "px");
			        },
			        _position: function(offset) {
			            this.uiChatbox.css("right", offset);
			        }
			    });
			}(jQuery));

			
			function getchat()
			{
				/*
					now if box is not null,
					we are toggling chat box.
				*/
				if(box)
				{
					/*
						below code will hide the chatbox that 
						is active, when first clicked on toggle button
					*/
					box.chatbox("option", "boxManager").toggleBox();
				}
				else
				{
					/*
						if box variable is null then we will create
						chat-box.
					*/
					box = $("#chat_div").chatbox(
					{
						/*
							unique id for chat box
						*/
						id:"Guest",
                        user:
						{
							key : "value"
						},
						/*
							Title for the chat box
						*/
						title : "Chatbox",
						/*
							messageSend as name suggest,
							this will called when message sent.
							and for demo we have appended sent message to our log div.
						*/
						messageSent : function(id, user, msg)
						{
							$("#log").append(id + " said: " + msg + "<br/>");
                            $("#chat_div").chatbox("option", "boxManager").addMsg(id, msg);
                        }
					});
				}
			};
		
