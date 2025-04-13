FROM ubuntu:24.04

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    curl \
    unzip \
    wget \
    git \
    zip \
    && apt-get clean

RUN curl -s https://api.github.com/repos/JetBrains/kotlin/releases/latest \
    | grep "browser_download_url.*kotlin-compiler.*zip" \
    | cut -d '"' -f 4 \
    | xargs curl -LO && \
    unzip kotlin-compiler-*.zip && \
    mv kotlinc /opt/kotlinc && \
    ln -s /opt/kotlinc/bin/* /usr/local/bin/ && \
    rm kotlin-compiler-*.zip

CMD ["bash"]