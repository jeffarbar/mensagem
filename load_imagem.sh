docker build -t jeffersonfarias/mensagem-backend:1.0.1  -f Dockerfile .
docker login --username=jeffersonfarias
docker push jeffersonfarias/mensagem-backend:1.0.1
