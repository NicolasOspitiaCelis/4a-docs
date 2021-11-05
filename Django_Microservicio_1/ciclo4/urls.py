from django.contrib import admin
from django.urls import path
from rest_framework_simplejwt.views import (
    TokenObtainPairView, TokenRefreshView)
from ciclo4App import views

urlpatterns = [
    path('admin/', admin.site.urls),
]
