from django.shortcuts import render

from myapp.models import KkboxSong, UserlikeRecord
from django.http import JsonResponse
from django.http import HttpResponse
from django.core import serializers
import json


def SQLKkboxSong(request):
        nameList = []
                
        button = request.GET.get("button")
        #print(button)
        if button:
                U = UserlikeRecord.objects.filter(user_like=button)
                
                for i in range(len(U)): #range(len(name_list))
                        A=(U[i].item.song_name)
                        B=(U[i].item.artist)
                        dict = {}
                        dict['songname'] = A
                        dict['artist'] = B
                        nameList.append(dict)
                C={elem["songname"]:elem for elem in nameList}.values()
                #他的格式為dict_value,JsonResponse只吃list、dict
                print(list(C))
                #B=json.dumps(list(A))
                return JsonResponse(list(C),safe=False) 
        else: return HttpResponse("NO")


#ANDROID偵錯
# def SQLKkboxSong(request):
#                 nameList = []
                
#         #button = request.GET.get("button")
#         #print(button)
#         #if button:
#                 U = UserlikeRecord.objects.filter(user_like="like")
                
#                 for i in range(len(U)): #range(len(name_list))
#                         A=(U[i].item.song_name)
#                         B=(U[i].item.artist)
#                         dict = {}
#                         dict['songname'] = A
#                         dict['artist'] = B
#                         nameList.append(dict)
#                 C={elem["songname"]:elem for elem in nameList}.values()
#                 #他的格式為dict_value,JsonResponse只吃list、dict
#                 print(list(C))
#                 #B=json.dumps(list(A))
#                 return JsonResponse(list(C),safe=False) 
#         #else: return HttpResponse("NO")