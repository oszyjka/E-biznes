FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    software-properties-common \
    curl \
    wget \
    gnupg \
    lsb-release \
    && add-apt-repository ppa:deadsnakes/ppa \
    && apt-get update && apt-get install -y \
    python3.10 \
    python3.10-venv \
    python3.10-distutils \
    && ln -s /usr/bin/python3.10 /usr/bin/python \
    && apt-get clean

CMD ["python", "--version"]