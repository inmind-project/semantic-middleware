# <NLSC> Unrestricted Natural Language-based Service Composition through Sentence Embeddings

Status for master branch:

[//]: # (this is a comment: see this link for badges using travis-CI, codecov, etc: https://github.com/mlindauer/SMAC3/blob/warmstarting_multi_model/README.md) 
![build](https://img.shields.io/badge/build-passing-green.svg?cacheSeconds=2592000) 
![test](https://img.shields.io/badge/test-passing-green.svg?cacheSeconds=2592000) 
![coverage](https://img.shields.io/badge/coverage-90%25-yellowgreen.svg?cacheSeconds=2592000) 
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e3935f15c7fe495191910e9ab92d9143)](https://www.codacy.com/app/ojrlopez27/semantic-middleware?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ojrlopez27/nl-service-composition&amp;utm_campaign=Badge_Grade)

Implementation:

![version](https://img.shields.io/badge/version-1.2-blue.svg?cacheSeconds=2592000)
![language](https://img.shields.io/badge/language-Java-yellowgreen.svg?cacheSeconds=2592000) 
![language](https://img.shields.io/badge/language-C++-yellowgreen.svg?cacheSeconds=2592000) 
![language](https://img.shields.io/badge/language-Python-yellowgreen.svg?cacheSeconds=2592000) 
![dependencies](https://img.shields.io/badge/dependencies-sent2vec-orange.svg?cacheSeconds=2592000)

## Overview

NLSC uses sentence embeddings for matching high-level natural-language-based user requests to low-level service descriptions without needing to use syntactic or semantic description languages (e.g., WSDL, OWL-S, etc.)

This project is composed of multiple sub-projects, but for a minimal execution you only need to run sub-projects 1 and 2:
1. **sent2vec** (server): this sub-project uses sentence embeddings based on an unsupervised version of Facebook FastText, and an extension of word2vec (CBOW) to sentences. It is written in C++ code and provides some Python wrappers.
2. **composition** (client): this is a Java sub-project (further portable to Android) that defines:
    - Annotated abstract services (APIS) 
    - Annotated concrete services
    - Compositional Mechanisms (pipeline syle, for now)
    - Name Entity Recognition (based on Stanford NER Libraries) - it recognizes places, names, organizations, numbers, dates, and money
    - A Working Memory module for storing temporary results (from inferences, current state, etc.)
    - ZMQ communication with sent2vec server
    - easy-rule engine and MVEL rules for composition
    - POJOs
    - Utils
3. **experiments/performance** (server): this is a Java server that is listening to multiple clients which tell the server how long they took to perform the composition task. This server, once has collected all the time logs from all the clients, stores on a file both each single time entry and the average time for all clients.
4. **experiments/mechanical-turk** (client-server): this is a web application written in tornado python. Basically, this is a chat interface that allows human users to interact with an agent in the background who performs service composition
5. **experiments/android** (client): this is an android client app that allows a human user to communicate with an agent running in the server who performs service composition
6. **osgi** (server): this is the migration of composition project to OSG-i (experimental)
7. **sent2vec-client** (client): this is a test client that communicates with sent2vec server (only for testing pruposes)
    
## Setup
1. **sent2vec** server: follow the instructions on the README file under the sent2vec folder, and:
    - Download Cython. If using mac, 'pip install Cython'
    - Install module globally:
	      - /usr/local/bin/python2.7 setup.py build_ext
	      - sudo -H pip install .
	      - pip3 install nltk
    - Download pre-trained models from https://github.com/epfml/sent2vec, more specifically, wiki bigrams (16GB) and toronto unigrams (2GB) -- though you can try different models, particularly, we should try twitter model since it is bigger in size and precision may be improved. Put the models under the folder sent2vec
    - Modify run.sh bash script. If you want more precision (though it will be slower) then use wiki model, otherwise use toronto model which is faster
2. **composition** client:
    - Modify config.properties and point the path variables to your local folders
    - Modify task-def-script to reflect the high level description of a task or plan (e.g., plan a trip to a place on a range of dates)
    - Modify task-exec-script to reflect a contextualization of the high-level description (e.g., plan a trip to Boston from August 29 to September 11)
    - If you are **NOT** doing performance tests, then you should disable the corresponding option on the confi.properties file (performance.test.enable = false)
3. **experiments/performance** server:
	- Install GNU parallel: (brew install parallel, or port install parallel)
	- Modify build.gradle to create a jar
	- Write a script for running the jar
	- Modify config.properties so the upper threshold is very low (0.1) andlower (0.01)
	- Modify task-exec-script file in such a way that all the params are provided in each sentence (date, location, etc).
	- Write a script using GNU parallel (https://stackoverflow.com/a/21197038) that loads the scripts that runs the jar
	- Run the performance Java server
	- Run the parallel script for 10, 100, 1000, 10,000 processes (clients)
4. Mechanical Turk Experiments:
	- Make sure you are using wiki pre-trained model (it is more accurate). Modify sent2vec/run.sh script.
	- If you have added new services or api's (**only**) then:
		- add words to the blacklist (if needed) in composiiton/.../DatasetCleaner
	- Configure the Chat Web App (experiments/mechanical-turk/chat):
		- brew install python
		- brew upgrade python
		- check version (should be minimum 3.6): /usr/local/bin/python3.6-32 --version
		- install tornado globally:
			- /usr/local/bin/python3.6-32 -m pip install tornado
		- if you are using DDNS (noip.com):
			- make sure every time you move to another network (e.g., from home to campus) you will have to update the ip address in noip.com (user inmind.yahoo.2015@gmail)
			- if working from home, you hill have to open (forward) TCP and UPP ports 5555, 5556 and 8888 (using your admin web console of your router) otherwise, while testing locally, you can just test using localhost (though, during tests with turkers, you will need to make sure app.composer.ddns.net is available)
	- run sent2vec
	- change config.properties (variable paths) on java composition project
	- run Java composition project (MUF_MKTLauncher.java)
	- open a web browser and type either app.composer.ddns.net:8888 or localhost:8888
  
## Execution
- There are two modes of execution:
    1. Batch: it reads both high-level and contextualized descriptions from composition/task-def-script and composition/task-exec-script files respectively
    2. Step-by-step: it waits for user to type each entry on the console rather than reading them from the files
- The execution mode can be changed on class CompositionLauncher, when InputController is instantiated
- First, execute bash script 'sent2vec/run.sh, wait few minutes (depending on the size of the pre-trained model), and then run the Java project (though, if you run Java first, it will wait until sent2vec has established the connection)
- Once the sent2vec server is running, the pre-trained model is loaded only once, so you can stop and re-start the Java client as many times as you want without having to stop the server (though sometimes the server gets blocked, so you have to stop it)

## Playing with it
- QoS features (e.g., Connectivity, Battery Consumption, etc.) are static for now, but you can write a decay function for battery consumption and a random function for connectivity in CompositionController.addPhoneStatusToWM, just to see how different services are grounded. Or you can run an experiment once, see the results, then modify the values for QoS, run a second experiment, and compare the results
- Modify both task-def-script and task-exec-script files, and see what happens. However, try the provided example before trying other scenarios
- Modify the thresholds on ServiceExecutor class. See explanation on method getServiceMethod
- Try both execution modes (batch, step-by-step)
- Create your own services following the provided ones as templates (using annotations and so on)
- Add synonyms in SemanticController (it will be helpful when retrieving data from working memory)
- Try different pre-trained models

## TO-DO
- There are specific TODO tags in the Java code that you can reference, however, the current state can allow us do some experiments and present it as a demo.
- Port the Java code to Android
- Move the Stanford NER code to the server side (not sure if we can do this in Android)
- Replace SemanticController by a proper semantic mechanism for getting word relatedness and synonyms (e.g., Word2Vec, WordNet, etc.)
- Create and train our own models? why not... maybe using what users say during the user study, however, we would need to collect a big dataset
- Not sure if a cost fucntion based on similarity and QoS features should be included, or just keep using the similarity thresholds and delta
- Currently composition is linear (pipeline style) but we need to introduce control structures (e.g., loops, conditionals, etc.). It would be interesting to convert natural language commands into rules using MVEL rules (or maybe LIA/Sugilite)
- Mechanism for mapping entity extraction with parameters is pretty basic, we should improve it
- Running multiple times the same experiment produces different similarities (sent2vec) so sometimes the service grounding cannot be performed since similarities are really low.... not sure why this happens (it occurs randomly and rarely)
- etc.

## How to cite our work

[SCC 2019 paper](https://arxiv.org/abs/1901.07910):

```
@inproceedings{nlsc:2019,
  title = {Unrestricted Natural Language-based Service Composition through Sentence Embeddings},
  author = {{Romero}, {Oscar} J. and {Dangi}, {Ankit and} {Akoju}, {Sushma}},
  booktitle = {Services Computing Conference},
  year          = "2019",
  pages         = "00--00"
}
```
