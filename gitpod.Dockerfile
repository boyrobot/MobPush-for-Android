FROM gitpod/workspace-full-vnc:2022-07-20-05-50-58
SHELL ["/bin/bash", "-c"]

ENV ANDROID_HOME=/home/gitpod/androidsdk \
    FLUTTER_VERSION=3.0.5-stable

ENV PATH="$HOME/flutter/bin:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH"

# Install dart
# USER root
# RUN curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
#     && curl -fsSL https://storage.googleapis.com/download.dartlang.org/linux/debian/dart_stable.list > /etc/apt/sources.list.d/dart_stable.list \
#     && install-packages build-essential dart libkrb5-dev gcc make gradle android-tools-adb android-tools-fastboot

# Install Open JDK
# RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
#     sdk install java 11.0.16-amzn && \
#     sdk default java 11.0.16-amzn"
# /home/gitpod/.sdkman/candidates/java/11.0.16.fx-zulu/bin/java

# Install Android SDK Manager
USER gitpod
RUN  wget https://dl.google.com/android/repository/commandlinetools-linux-8092744_latest.zip \
    && mkdir -p $ANDROID_HOME/cmdline-tools/latest \
    && unzip commandlinetools-linux-*.zip -d $ANDROID_HOME \
    && rm -f commandlinetools-linux-*.zip \
    && mv $ANDROID_HOME/cmdline-tools/bin $ANDROID_HOME/cmdline-tools/latest \
    && mv $ANDROID_HOME/cmdline-tools/lib $ANDROID_HOME/cmdline-tools/latest

ENV JAVA_HOME=/home/gitpod/.sdkman/candidates/java/current
# Install Android Image version 31
USER gitpod
RUN yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platform-tools" "build-tools;31.0.0" "platforms;android-31"
# RUN yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "system-images;android-31;google_apis;x86_64"
# RUN echo no | $ANDROID_HOME/cmdline-tools/latest/bin/avdmanager create avd -n avd31 -k "system-images;android-31;google_apis;x86_64"

# Install flutter
USER gitpod
RUN cd /home/gitpod \
    && wget https://storage.googleapis.com/flutter_infra_release/releases/stable/linux/flutter_linux_${FLUTTER_VERSION}.tar.xz \
    && tar -xvf flutter*.tar.xz \
    && rm -f flutter*.tar.xz

RUN flutter/bin/flutter precache

# Install Google Chrome
USER root
RUN apt-get update \
  && apt-get install -y apt-transport-https \
  && curl -sSL https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
  && echo "deb [arch=amd64] https://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
  && apt-get update \
  && sudo apt-get install -y google-chrome-stable

# misc deps
RUN apt-get install -y \
  libasound2-dev \
  libgtk-3-dev \
  libnss3-dev \
  fonts-noto \
  fonts-noto-cjk

# For Qt WebEngine on docker
ENV QTWEBENGINE_DISABLE_SANDBOX 1