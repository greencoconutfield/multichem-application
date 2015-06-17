Uses MySql for development and H2 for unit tests.

 Need to install ruby and compass
To get the project ready
========================
npm install
bower install

grunt build
(This will prepare the client side code. ignoring this will result in client side resources like css, images not available while running.)

To run the client side server (optional)
========================================
grunt server

Compile the server side code
============================
mvn clean install

Run the server
==============
mvn spring-boot:run

To run the application with hot reload enabled, add the following arguments to your JVM:
"-javaagent:spring_loaded/springloaded-jhipster.jar -noverify -Dspringloaded=plugins=io.github.jhipster.loaded.instrument.JHipsterLoadtimeInstrumentationPlugin"

Cloudbees JENKINS Pre Steps SHELL
=================================
```
curl -s -o use-node https://repository-cloudbees.forge.cloudbees.com/distributions/ci-addons/node/use-node
NODE_VERSION=0.11.12 . ./use-node
npm cache clean
[ -d node_modules/grunt] || npm install -g grunt
[ -d node_modules/grunt-cli] || npm install -g grunt-cli
[ -d node_modules/bower] || npm install -g bower

curl -s -o ./use-ruby https://repository-cloudbees.forge.cloudbees.com/distributions/ci-addons/ruby/use-ruby
RUBY_VERSION=2.0.0-p247 \
source ./use-ruby
gem install --conservative compass

node --version
compass --version

[ -d $HOME/bin ] || mkdir $HOME/bin
[ -f $HOME/bin/node ] || ln -s ${BASE}/node/$node_name/bin/node $HOME/bin/node
[ -f $HOME/bin/npm ] || ln -s ${BASE}/node/$node_name/bin/npm $HOME/bin/npm
[ -f $HOME/bin/ruby ] || ln -s ${BASE}/ruby/${ruby_name}/bin/ruby $HOME/bin/ruby
[ -f $HOME/bin/compass ] || ln -s ${BASE}/ruby/${ruby_name}/bin/compass $HOME/bin/compass
[ -f $HOME/bin/bower ] || ln -s ${BASE}/node/$node_name/bin/bower $HOME/bin/bower
[ -f $HOME/bin/grunt ] || ln -s ${BASE}/node/$node_name/bin/grunt $HOME/bin/grunt
```
========================================================================================================================

*** ui-Router basics

-- http://www.ng-newsletter.com/posts/angular-ui-router.html

-- https://github.com/marcoslin/angularAMD/issues/9

-- https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions#issue-problems-when-using-ng-view-alongside-ui-view

-- http://www.frederiknakstad.com/2014/02/09/ui-router-in-angular-client-side-auth/


** More about lazy loading

-- http://ify.io/lazy-loading-in-angularjs/

-- https://github.com/ifyio/angularjs-lazy-loading-with-requirejs

-- * http://marcoslin.github.io/angularAMD/#/home

-- ** https://github.com/DanWahlin/CustomerManager

-- * http://weblogs.asp.net/dwahlin/dynamically-loading-controllers-and-views-with-angularjs-and-requirejs


Currently trying requirejs using

http://thaiat.github.io/blog/2014/02/26/angularjs-and-requirejs-for-very-large-applications/

RequireJs reference - https://github.com/tnajdek/angular-requirejs-seed/blob/master/app/js/app.js


Try this for proper login redirection

- http://espeo.pl/authentication-in-angularjs-application/

- https://github.com/mrgamer/angular-login-example

- https://github.com/fnakstad/angular-client-side-auth

*** Check ui-router for alternate way of routing..


* Angular breadcrumb reference:

- https://github.com/ncuillery/angular-breadcrumb/wiki/API-Reference


* UNIT TEST REF

- https://github.com/matiasalvarez87/angular-require-karma/blob/master/tests/unit/controllers/homeControllerSpec.js

- angularAMD

- http://stackoverflow.com/questions/23090302/undefined-is-not-an-object-evaluating-app-register-service-when-using-requ

LAZY LOADING TEST:

------------------

home (login) - 44 requests - 1.7MB

home (after login) - 5 req - 2.7KB

Metrics - 4 req - 25.6KB

logs - 3 req - 11.5KB

API - 20 req - 502KB

