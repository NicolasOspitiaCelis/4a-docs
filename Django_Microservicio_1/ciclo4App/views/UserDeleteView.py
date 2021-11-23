from rest_framework import generics, status
from rest_framework.response import Response
from ciclo4App.models.User import User
from ciclo4App.serializers.UserSerializer import UserSerializer
from rest_framework.permissions import IsAuthenticated


class UserDeleteView(generics.RetrieveUpdateDestroyAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    permission_classes = [IsAuthenticated]

    def destroy(self, request, *args, **kwargs):
        pk = kwargs["pk"]
        if User.objects.filter(id=pk).exists():
            instance = User.objects.get(pk=pk)
            self.perform_destroy(instance)
        else:
            return Response({'message': 'Usuario no existe en la base de datos'}, status=status.HTTP_404_NOT_FOUND)
        return Response({"message": "Se eliminó el usuario correctamente"}, status=status.HTTP_200_OK)
