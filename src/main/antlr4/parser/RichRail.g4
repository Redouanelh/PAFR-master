grammar RichRail;

// Shortcuts
GET         : 'get';
ADD         : 'add';
NEW         : 'new';
DELETE      : 'delete';
REMOVE      : 'remove';
TO          : 'to';
FROM        : 'from';
HELP        : 'help';
GETALL      : 'getall';
SAVE        : 'save';
LOAD        : 'load';

// Commands
command     : newcommand | addcommand | getcommand | delcommand | remcommand | helpcommand | getallcommand | savecommand | loadcommand;
newcommand  : NEW TYPE=('passenger' | 'freight' | 'locomotive') ID ARG*;
addcommand  : ADD ID TO ID;
getcommand  : GET ID;
delcommand  : DELETE TYPE=('locomotive' | 'wagon') ID;
remcommand  : REMOVE ID FROM ID;
helpcommand : HELP;
getallcommand : GETALL;
savecommand : SAVE FILENAME;
loadcommand : LOAD FILENAME;

// Tokens
ID          : ('a'..'z')('a'..'z'|'0'..'9')*;
ARG         : ('0'..'9')+;
WHITESPACE  : [ \t\r\n\u000C] -> skip;
FILENAME    : ('a'..'z'|'0'..'9'|'.')+;

