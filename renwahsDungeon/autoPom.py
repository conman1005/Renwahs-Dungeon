from xml.dom import minidom
from os import rename, remove
import time

#Open pom.xml file as parsed xml
pomFile = minidom.parse("pom.xml")

#Fill the elements with the previously generated texts
gT = pomFile.getElementsByTagName("groupId")[0]
nT = pomFile.getElementsByTagName("name")[0]
groupId = gT.firstChild.nodeValue.lower()
name = nT.firstChild.nodeValue.lower()

#Remove build tag from file
bT = pomFile.getElementsByTagName("build")[0]
pT = pomFile.getElementsByTagName("project")[0]
pT.removeChild(bT)

#Create new build tag and a dependencies from add.xml file
add = minidom.parse("add.xml")
dT = add.getElementsByTagName("dependencies")[0]
newBT = add.getElementsByTagName("build")[0]

#Append the new tags to the file
pT.appendChild(dT)
pT.appendChild(newBT)

#Write to file
file_handle = open("pom.xml","w")
pomFile.writexml(file_handle)

#Close writer
file_handle.close()

#Replace nbactions.xml
remove("nbactions.xml")
rename("nbactions_new.xml", "nbactions.xml")

#Edit module-info.java
file_handle = open("module-info.java","r")
lines = file_handle.readlines()
lines[0] = "module " + groupId + " {"
lines[6] = "opens " + groupId + "." + name + ";"
file_handle.close()

#Write to the file
file_handle = open("module-info.java","w")
for i in lines:
    file_handle.write(i)

#Close writer
file_handle.close()

#Move module-info.java into correct directory
rename("module-info.java", "src/main/java/module-info.java")

#YOTE
print("Yote to you by Cameron Fairchild")
time.sleep(5)
