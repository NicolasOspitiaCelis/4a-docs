from rest_framework import serializers
from ciclo4App.models.User import User


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'password', 'name', 'email']
