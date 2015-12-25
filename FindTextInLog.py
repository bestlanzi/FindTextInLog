import sys
import re
import time

def readfile():
    information = []
    count = 0
    count1 = 0
    fileDir = "c:/test.log"

    #需要找到的关键字符串1
    findString = "保存需求上报流程数据"
    #需要找到的关键字符串2
    findString1 = "计通[2015]66号《关于下发《基于全过程的管线资源建设管理要求》的通知》"

    #遍历出一个大数组出来
    for line in open(fileDir, encoding = 'gb2312'):
        pattern = re.compile('[2]\d\d\d-\d\d-\d\d \d\d:\d\d:\d\d')
        match = pattern.match(line)

        #如果找到了时间戳
        if match:
            count1 +=1
            date = match.group()
            item = [date,line]
            information.append(item)
        else:
            information[len(information)-1][1] = information[len(information)-1][1] + line
            
        count += 1

    #下面开始找字符串
    resultCount = 0
    for a in information:
        if boolFindTxt(a[1], findString):
            if boolFindTxt(a[1],findString1):
                print(a[1])
                resultCount += 1
            
                
    print ("line total:",count)
    print ("Time stamp matched ",count1)
    print ("result line total: ", resultCount)

#从一个字符串中找到txt
def boolFindTxt(line, txt):
    result = line.find(txt)
    if result == -1:
        return False
    else:
        return True

start = time.clock()    
readfile()
end = time.clock()

print ("read:{} s".format(end -start))
