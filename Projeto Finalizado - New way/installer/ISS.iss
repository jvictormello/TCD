; -- Example1.iss --
; Demonstrates copying 3 files and creating an icon.

; SEE THE DOCUMENTATION FOR DETAILS ON CREATING .ISS SCRIPT FILES!

[Setup]
AppName=TCD
AppVersion=2.0
DefaultDirName={pf}\TCD
DefaultGroupName=TCD
UninstallDisplayIcon={app}\TCD.jar
Compression=lzma2
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile="C:\Users\sigma\Desktop\TCD\images\logo\LOGOsketchup.ico"
OutputBaseFilename="TCD Setup"

[Files]
Source: "TCD.jar"; DestDir: "{app}"
Source: "images\*"; DestDir: "{app}\images"
Source: "images\botao\*"; DestDir: "{app}\images\botao"
Source: "images\direcao\*"; DestDir: "{app}\images\direcao"
Source: "images\falhas\*"; DestDir: "{app}\images\falhas"
Source: "images\logo\*"; DestDir: "{app}\images\logo"
Source: "fundamentos\*"; DestDir: "{app}\fundamentos"
Source: "ajuda\*"; DestDir: "{app}\ajuda"

[Languages]       
Name: en; MessagesFile: "compiler:Default.isl"
Name: nl; MessagesFile: "compiler:Languages\Dutch.isl"
Name: de; MessagesFile: "compiler:Languages\German.isl"
Name: "ptBR"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl" 

[Icons]
Name: "{commonprograms}\TCD"; Filename: "{app}\TCD.jar"
Name: "{group}\TCD"; Filename: "{app}\TCD.jar"; WorkingDir: "{app}"; IconFilename: "{app}\images\logo\LOGOsketchup.ico"; Comment: "TCD";
Name: "{commondesktop}\TCD"; Filename: "{app}\TCD.jar"; WorkingDir: "{app}"; IconFilename: "{app}\images\logo\LOGOsketchup.ico"; Comment: "TCD";