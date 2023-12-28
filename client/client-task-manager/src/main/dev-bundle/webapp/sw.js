try{self["workbox:core:7.0.0"]&&_()}catch{}const O=(s,...e)=>{let t=s;return e.length>0&&(t+=` :: ${JSON.stringify(e)}`),t},M=O;class l extends Error{constructor(e,t){const a=M(e,t);super(a),this.name=e,this.details=t}}const S=new Set,f={googleAnalytics:"googleAnalytics",precache:"precache-v2",prefix:"workbox",runtime:"runtime",suffix:typeof registration<"u"?registration.scope:""},R=s=>[f.prefix,s,f.suffix].filter(e=>e&&e.length>0).join("-"),W=s=>{for(const e of Object.keys(f))s(e)},I={updateDetails:s=>{W(e=>{typeof s[e]=="string"&&(f[e]=s[e])})},getGoogleAnalyticsName:s=>s||R(f.googleAnalytics),getPrecacheName:s=>s||R(f.precache),getPrefix:()=>f.prefix,getRuntimeName:s=>s||R(f.runtime),getSuffix:()=>f.suffix};function j(s,e){const t=new URL(s);for(const a of e)t.searchParams.delete(a);return t.href}async function F(s,e,t,a){const i=j(e.url,t);if(e.url===i)return s.match(e,a);const n=Object.assign(Object.assign({},a),{ignoreSearch:!0}),r=await s.keys(e,n);for(const c of r){const o=j(c.url,t);if(i===o)return s.match(c,a)}}let g;function q(){if(g===void 0){const s=new Response("");if("body"in s)try{new Response(s.body),g=!0}catch{g=!1}g=!1}return g}class H{constructor(){this.promise=new Promise((e,t)=>{this.resolve=e,this.reject=t})}}async function B(){for(const s of S)await s()}const $=s=>new URL(String(s),location.href).href.replace(new RegExp(`^${location.origin}`),"");function T(s){return new Promise(e=>setTimeout(e,s))}function U(s,e){const t=e();return s.waitUntil(t),t}async function G(s,e){let t=null;if(s.url&&(t=new URL(s.url).origin),t!==self.location.origin)throw new l("cross-origin-copy-response",{origin:t});const a=s.clone(),i={headers:new Headers(a.headers),status:a.status,statusText:a.statusText},n=e?e(i):i,r=q()?a.body:await a.blob();return new Response(r,n)}function z(){self.addEventListener("activate",()=>self.clients.claim())}try{self["workbox:precaching:7.0.0"]&&_()}catch{}const Q="__WB_REVISION__";function J(s){if(!s)throw new l("add-to-cache-list-unexpected-type",{entry:s});if(typeof s=="string"){const n=new URL(s,location.href);return{cacheKey:n.href,url:n.href}}const{revision:e,url:t}=s;if(!t)throw new l("add-to-cache-list-unexpected-type",{entry:s});if(!e){const n=new URL(t,location.href);return{cacheKey:n.href,url:n.href}}const a=new URL(t,location.href),i=new URL(t,location.href);return a.searchParams.set(Q,e),{cacheKey:a.href,url:i.href}}class X{constructor(){this.updatedURLs=[],this.notUpdatedURLs=[],this.handlerWillStart=async({request:e,state:t})=>{t&&(t.originalRequest=e)},this.cachedResponseWillBeUsed=async({event:e,state:t,cachedResponse:a})=>{if(e.type==="install"&&t&&t.originalRequest&&t.originalRequest instanceof Request){const i=t.originalRequest.url;a?this.notUpdatedURLs.push(i):this.updatedURLs.push(i)}return a}}}class Y{constructor({precacheController:e}){this.cacheKeyWillBeUsed=async({request:t,params:a})=>{const i=(a==null?void 0:a.cacheKey)||this._precacheController.getCacheKeyForURL(t.url);return i?new Request(i,{headers:t.headers}):t},this._precacheController=e}}try{self["workbox:strategies:7.0.0"]&&_()}catch{}function y(s){return typeof s=="string"?new Request(s):s}class Z{constructor(e,t){this._cacheKeys={},Object.assign(this,t),this.event=t.event,this._strategy=e,this._handlerDeferred=new H,this._extendLifetimePromises=[],this._plugins=[...e.plugins],this._pluginStateMap=new Map;for(const a of this._plugins)this._pluginStateMap.set(a,{});this.event.waitUntil(this._handlerDeferred.promise)}async fetch(e){const{event:t}=this;let a=y(e);if(a.mode==="navigate"&&t instanceof FetchEvent&&t.preloadResponse){const r=await t.preloadResponse;if(r)return r}const i=this.hasCallback("fetchDidFail")?a.clone():null;try{for(const r of this.iterateCallbacks("requestWillFetch"))a=await r({request:a.clone(),event:t})}catch(r){if(r instanceof Error)throw new l("plugin-error-request-will-fetch",{thrownErrorMessage:r.message})}const n=a.clone();try{let r;r=await fetch(a,a.mode==="navigate"?void 0:this._strategy.fetchOptions);for(const c of this.iterateCallbacks("fetchDidSucceed"))r=await c({event:t,request:n,response:r});return r}catch(r){throw i&&await this.runCallbacks("fetchDidFail",{error:r,event:t,originalRequest:i.clone(),request:n.clone()}),r}}async fetchAndCachePut(e){const t=await this.fetch(e),a=t.clone();return this.waitUntil(this.cachePut(e,a)),t}async cacheMatch(e){const t=y(e);let a;const{cacheName:i,matchOptions:n}=this._strategy,r=await this.getCacheKey(t,"read"),c=Object.assign(Object.assign({},n),{cacheName:i});a=await caches.match(r,c);for(const o of this.iterateCallbacks("cachedResponseWillBeUsed"))a=await o({cacheName:i,matchOptions:n,cachedResponse:a,request:r,event:this.event})||void 0;return a}async cachePut(e,t){const a=y(e);await T(0);const i=await this.getCacheKey(a,"write");if(!t)throw new l("cache-put-with-no-response",{url:$(i.url)});const n=await this._ensureResponseSafeToCache(t);if(!n)return!1;const{cacheName:r,matchOptions:c}=this._strategy,o=await self.caches.open(r),d=this.hasCallback("cacheDidUpdate"),p=d?await F(o,i.clone(),["__WB_REVISION__"],c):null;try{await o.put(i,d?n.clone():n)}catch(u){if(u instanceof Error)throw u.name==="QuotaExceededError"&&await B(),u}for(const u of this.iterateCallbacks("cacheDidUpdate"))await u({cacheName:r,oldResponse:p,newResponse:n.clone(),request:i,event:this.event});return!0}async getCacheKey(e,t){const a=`${e.url} | ${t}`;if(!this._cacheKeys[a]){let i=e;for(const n of this.iterateCallbacks("cacheKeyWillBeUsed"))i=y(await n({mode:t,request:i,event:this.event,params:this.params}));this._cacheKeys[a]=i}return this._cacheKeys[a]}hasCallback(e){for(const t of this._strategy.plugins)if(e in t)return!0;return!1}async runCallbacks(e,t){for(const a of this.iterateCallbacks(e))await a(t)}*iterateCallbacks(e){for(const t of this._strategy.plugins)if(typeof t[e]=="function"){const a=this._pluginStateMap.get(t);yield n=>{const r=Object.assign(Object.assign({},n),{state:a});return t[e](r)}}}waitUntil(e){return this._extendLifetimePromises.push(e),e}async doneWaiting(){let e;for(;e=this._extendLifetimePromises.shift();)await e}destroy(){this._handlerDeferred.resolve(null)}async _ensureResponseSafeToCache(e){let t=e,a=!1;for(const i of this.iterateCallbacks("cacheWillUpdate"))if(t=await i({request:this.request,response:t,event:this.event})||void 0,a=!0,!t)break;return a||t&&t.status!==200&&(t=void 0),t}}class D{constructor(e={}){this.cacheName=I.getRuntimeName(e.cacheName),this.plugins=e.plugins||[],this.fetchOptions=e.fetchOptions,this.matchOptions=e.matchOptions}handle(e){const[t]=this.handleAll(e);return t}handleAll(e){e instanceof FetchEvent&&(e={event:e,request:e.request});const t=e.event,a=typeof e.request=="string"?new Request(e.request):e.request,i="params"in e?e.params:void 0,n=new Z(this,{event:t,request:a,params:i}),r=this._getResponse(n,a,t),c=this._awaitComplete(r,n,a,t);return[r,c]}async _getResponse(e,t,a){await e.runCallbacks("handlerWillStart",{event:a,request:t});let i;try{if(i=await this._handle(t,e),!i||i.type==="error")throw new l("no-response",{url:t.url})}catch(n){if(n instanceof Error){for(const r of e.iterateCallbacks("handlerDidError"))if(i=await r({error:n,event:a,request:t}),i)break}if(!i)throw n}for(const n of e.iterateCallbacks("handlerWillRespond"))i=await n({event:a,request:t,response:i});return i}async _awaitComplete(e,t,a,i){let n,r;try{n=await e}catch{}try{await t.runCallbacks("handlerDidRespond",{event:i,request:a,response:n}),await t.doneWaiting()}catch(c){c instanceof Error&&(r=c)}if(await t.runCallbacks("handlerDidComplete",{event:i,request:a,response:n,error:r}),t.destroy(),r)throw r}}class h extends D{constructor(e={}){e.cacheName=I.getPrecacheName(e.cacheName),super(e),this._fallbackToNetwork=e.fallbackToNetwork!==!1,this.plugins.push(h.copyRedirectedCacheableResponsesPlugin)}async _handle(e,t){const a=await t.cacheMatch(e);return a||(t.event&&t.event.type==="install"?await this._handleInstall(e,t):await this._handleFetch(e,t))}async _handleFetch(e,t){let a;const i=t.params||{};if(this._fallbackToNetwork){const n=i.integrity,r=e.integrity,c=!r||r===n;a=await t.fetch(new Request(e,{integrity:e.mode!=="no-cors"?r||n:void 0})),n&&c&&e.mode!=="no-cors"&&(this._useDefaultCacheabilityPluginIfNeeded(),await t.cachePut(e,a.clone()))}else throw new l("missing-precache-entry",{cacheName:this.cacheName,url:e.url});return a}async _handleInstall(e,t){this._useDefaultCacheabilityPluginIfNeeded();const a=await t.fetch(e);if(!await t.cachePut(e,a.clone()))throw new l("bad-precaching-response",{url:e.url,status:a.status});return a}_useDefaultCacheabilityPluginIfNeeded(){let e=null,t=0;for(const[a,i]of this.plugins.entries())i!==h.copyRedirectedCacheableResponsesPlugin&&(i===h.defaultPrecacheCacheabilityPlugin&&(e=a),i.cacheWillUpdate&&t++);t===0?this.plugins.push(h.defaultPrecacheCacheabilityPlugin):t>1&&e!==null&&this.plugins.splice(e,1)}}h.defaultPrecacheCacheabilityPlugin={async cacheWillUpdate({response:s}){return!s||s.status>=400?null:s}};h.copyRedirectedCacheableResponsesPlugin={async cacheWillUpdate({response:s}){return s.redirected?await G(s):s}};class ee{constructor({cacheName:e,plugins:t=[],fallbackToNetwork:a=!0}={}){this._urlsToCacheKeys=new Map,this._urlsToCacheModes=new Map,this._cacheKeysToIntegrities=new Map,this._strategy=new h({cacheName:I.getPrecacheName(e),plugins:[...t,new Y({precacheController:this})],fallbackToNetwork:a}),this.install=this.install.bind(this),this.activate=this.activate.bind(this)}get strategy(){return this._strategy}precache(e){this.addToCacheList(e),this._installAndActiveListenersAdded||(self.addEventListener("install",this.install),self.addEventListener("activate",this.activate),this._installAndActiveListenersAdded=!0)}addToCacheList(e){const t=[];for(const a of e){typeof a=="string"?t.push(a):a&&a.revision===void 0&&t.push(a.url);const{cacheKey:i,url:n}=J(a),r=typeof a!="string"&&a.revision?"reload":"default";if(this._urlsToCacheKeys.has(n)&&this._urlsToCacheKeys.get(n)!==i)throw new l("add-to-cache-list-conflicting-entries",{firstEntry:this._urlsToCacheKeys.get(n),secondEntry:i});if(typeof a!="string"&&a.integrity){if(this._cacheKeysToIntegrities.has(i)&&this._cacheKeysToIntegrities.get(i)!==a.integrity)throw new l("add-to-cache-list-conflicting-integrities",{url:n});this._cacheKeysToIntegrities.set(i,a.integrity)}if(this._urlsToCacheKeys.set(n,i),this._urlsToCacheModes.set(n,r),t.length>0){const c=`Workbox is precaching URLs without revision info: ${t.join(", ")}
This is generally NOT safe. Learn more at https://bit.ly/wb-precache`;console.warn(c)}}}install(e){return U(e,async()=>{const t=new X;this.strategy.plugins.push(t);for(const[n,r]of this._urlsToCacheKeys){const c=this._cacheKeysToIntegrities.get(r),o=this._urlsToCacheModes.get(n),d=new Request(n,{integrity:c,cache:o,credentials:"same-origin"});await Promise.all(this.strategy.handleAll({params:{cacheKey:r},request:d,event:e}))}const{updatedURLs:a,notUpdatedURLs:i}=t;return{updatedURLs:a,notUpdatedURLs:i}})}activate(e){return U(e,async()=>{const t=await self.caches.open(this.strategy.cacheName),a=await t.keys(),i=new Set(this._urlsToCacheKeys.values()),n=[];for(const r of a)i.has(r.url)||(await t.delete(r),n.push(r.url));return{deletedURLs:n}})}getURLsToCacheKeys(){return this._urlsToCacheKeys}getCachedURLs(){return[...this._urlsToCacheKeys.keys()]}getCacheKeyForURL(e){const t=new URL(e,location.href);return this._urlsToCacheKeys.get(t.href)}getIntegrityForCacheKey(e){return this._cacheKeysToIntegrities.get(e)}async matchPrecache(e){const t=e instanceof Request?e.url:e,a=this.getCacheKeyForURL(t);if(a)return(await self.caches.open(this.strategy.cacheName)).match(a)}createHandlerBoundToURL(e){const t=this.getCacheKeyForURL(e);if(!t)throw new l("non-precached-url",{url:e});return a=>(a.request=new Request(e),a.params=Object.assign({cacheKey:t},a.params),this.strategy.handle(a))}}let C;const v=()=>(C||(C=new ee),C);try{self["workbox:routing:7.0.0"]&&_()}catch{}const K="GET",m=s=>s&&typeof s=="object"?s:{handle:s};class b{constructor(e,t,a=K){this.handler=m(t),this.match=e,this.method=a}setCatchHandler(e){this.catchHandler=m(e)}}class te extends b{constructor(e,t,a){const i=({url:n})=>{const r=e.exec(n.href);if(r&&!(n.origin!==location.origin&&r.index!==0))return r.slice(1)};super(i,t,a)}}class ae{constructor(){this._routes=new Map,this._defaultHandlerMap=new Map}get routes(){return this._routes}addFetchListener(){self.addEventListener("fetch",e=>{const{request:t}=e,a=this.handleRequest({request:t,event:e});a&&e.respondWith(a)})}addCacheListener(){self.addEventListener("message",e=>{if(e.data&&e.data.type==="CACHE_URLS"){const{payload:t}=e.data,a=Promise.all(t.urlsToCache.map(i=>{typeof i=="string"&&(i=[i]);const n=new Request(...i);return this.handleRequest({request:n,event:e})}));e.waitUntil(a),e.ports&&e.ports[0]&&a.then(()=>e.ports[0].postMessage(!0))}})}handleRequest({request:e,event:t}){const a=new URL(e.url,location.href);if(!a.protocol.startsWith("http"))return;const i=a.origin===location.origin,{params:n,route:r}=this.findMatchingRoute({event:t,request:e,sameOrigin:i,url:a});let c=r&&r.handler;const o=e.method;if(!c&&this._defaultHandlerMap.has(o)&&(c=this._defaultHandlerMap.get(o)),!c)return;let d;try{d=c.handle({url:a,request:e,event:t,params:n})}catch(u){d=Promise.reject(u)}const p=r&&r.catchHandler;return d instanceof Promise&&(this._catchHandler||p)&&(d=d.catch(async u=>{if(p)try{return await p.handle({url:a,request:e,event:t,params:n})}catch(k){k instanceof Error&&(u=k)}if(this._catchHandler)return this._catchHandler.handle({url:a,request:e,event:t});throw u})),d}findMatchingRoute({url:e,sameOrigin:t,request:a,event:i}){const n=this._routes.get(a.method)||[];for(const r of n){let c;const o=r.match({url:e,sameOrigin:t,request:a,event:i});if(o)return c=o,(Array.isArray(c)&&c.length===0||o.constructor===Object&&Object.keys(o).length===0||typeof o=="boolean")&&(c=void 0),{route:r,params:c}}return{}}setDefaultHandler(e,t=K){this._defaultHandlerMap.set(t,m(e))}setCatchHandler(e){this._catchHandler=m(e)}registerRoute(e){this._routes.has(e.method)||this._routes.set(e.method,[]),this._routes.get(e.method).push(e)}unregisterRoute(e){if(!this._routes.has(e.method))throw new l("unregister-route-but-not-found-with-method",{method:e.method});const t=this._routes.get(e.method).indexOf(e);if(t>-1)this._routes.get(e.method).splice(t,1);else throw new l("unregister-route-route-not-registered")}}let w;const se=()=>(w||(w=new ae,w.addFetchListener(),w.addCacheListener()),w);function x(s,e,t){let a;if(typeof s=="string"){const n=new URL(s,location.href),r=({url:c})=>c.href===n.href;a=new b(r,e,t)}else if(s instanceof RegExp)a=new te(s,e,t);else if(typeof s=="function")a=new b(s,e,t);else if(s instanceof b)a=s;else throw new l("unsupported-route-type",{moduleName:"workbox-routing",funcName:"registerRoute",paramName:"capture"});return se().registerRoute(a),a}function ie(s,e=[]){for(const t of[...s.searchParams.keys()])e.some(a=>a.test(t))&&s.searchParams.delete(t);return s}function*ne(s,{ignoreURLParametersMatching:e=[/^utm_/,/^fbclid$/],directoryIndex:t="index.html",cleanURLs:a=!0,urlManipulation:i}={}){const n=new URL(s,location.href);n.hash="",yield n.href;const r=ie(n,e);if(yield r.href,t&&r.pathname.endsWith("/")){const c=new URL(r.href);c.pathname+=t,yield c.href}if(a){const c=new URL(r.href);c.pathname+=".html",yield c.href}if(i){const c=i({url:n});for(const o of c)yield o.href}}class re extends b{constructor(e,t){const a=({request:i})=>{const n=e.getURLsToCacheKeys();for(const r of ne(i.url,t)){const c=n.get(r);if(c){const o=e.getIntegrityForCacheKey(c);return{cacheKey:c,integrity:o}}}};super(a,e.strategy)}}function ce(s){const e=v(),t=new re(e,s);x(t)}function L(s){return v().getCacheKeyForURL(s)}function P(s){return v().matchPrecache(s)}function oe(s){v().precache(s)}function le(s,e){oe(s),ce(e)}class de extends b{constructor(e,{allowlist:t=[/./],denylist:a=[]}={}){super(i=>this._match(i),e),this._allowlist=t,this._denylist=a}_match({url:e,request:t}){if(t&&t.mode!=="navigate")return!1;const a=e.pathname+e.search;for(const i of this._denylist)if(i.test(a))return!1;return!!this._allowlist.some(i=>i.test(a))}}const ue={cacheWillUpdate:async({response:s})=>s.status===200||s.status===0?s:null};class fe extends D{constructor(e={}){super(e),this.plugins.some(t=>"cacheWillUpdate"in t)||this.plugins.unshift(ue),this._networkTimeoutSeconds=e.networkTimeoutSeconds||0}async _handle(e,t){const a=[],i=[];let n;if(this._networkTimeoutSeconds){const{id:o,promise:d}=this._getTimeoutPromise({request:e,logs:a,handler:t});n=o,i.push(d)}const r=this._getNetworkPromise({timeoutId:n,request:e,logs:a,handler:t});i.push(r);const c=await t.waitUntil((async()=>await t.waitUntil(Promise.race(i))||await r)());if(!c)throw new l("no-response",{url:e.url});return c}_getTimeoutPromise({request:e,logs:t,handler:a}){let i;return{promise:new Promise(r=>{i=setTimeout(async()=>{r(await a.cacheMatch(e))},this._networkTimeoutSeconds*1e3)}),id:i}}async _getNetworkPromise({timeoutId:e,request:t,logs:a,handler:i}){let n,r;try{r=await i.fetchAndCachePut(t)}catch(c){c instanceof Error&&(n=c)}return e&&clearTimeout(e),(n||!r)&&(r=await i.cacheMatch(t)),r}}class he extends D{constructor(e={}){super(e),this._networkTimeoutSeconds=e.networkTimeoutSeconds||0}async _handle(e,t){let a,i;try{const n=[t.fetch(e)];if(this._networkTimeoutSeconds){const r=T(this._networkTimeoutSeconds*1e3);n.push(r)}if(i=await Promise.race(n),!i)throw new Error(`Timed out the network response after ${this._networkTimeoutSeconds} seconds.`)}catch(n){n instanceof Error&&(a=n)}if(!i)throw new l("no-response",{url:e.url,error:a});return i}}importScripts("sw-runtime-resources-precache.js");self.skipWaiting();z();let A=[{url:".",revision:"0feac7e2533f7862f665ade58837e4fe"},{url:"VAADIN/build/FlowBootstrap-feff2646.js",revision:"86c7b60228bd60b898bd22f12bb25dd6"},{url:"VAADIN/build/FlowClient-341d667e.js",revision:"5b9fbf60cc8cc0bc2e4a23f47d1898d0"},{url:"VAADIN/build/generated-flow-imports-abb00327.js",revision:"85e96462807bc5f676998699d80f3f1e"},{url:"VAADIN/build/indexhtml-1c1f083a.js",revision:"c6155225091951e9e7aa8205729ca7c3"},{url:"VAADIN/build/vaadin-accordion-eed3b794-664aaf90.js",revision:"587fccc446c1ff27fdf03f7135e51805"},{url:"VAADIN/build/vaadin-accordion-heading-c0acdd6d-97e113b7.js",revision:"32e0fdc94046f3fd5a8c691f697f2fb1"},{url:"VAADIN/build/vaadin-accordion-panel-616e55d6-f7d8068c.js",revision:"12e87ef9157c21870865f25af2832828"},{url:"VAADIN/build/vaadin-app-layout-e56de2e9-46df4f89.js",revision:"1ad34e2835f545989fb2b1e98b602169"},{url:"VAADIN/build/vaadin-avatar-7599297d-4f71ba4a.js",revision:"675b418a5c8cc99ab1564365a132d038"},{url:"VAADIN/build/vaadin-big-decimal-field-e51def24-0aedfe0e.js",revision:"3789ff41eed11f42124361ee846dd90a"},{url:"VAADIN/build/vaadin-board-828ebdea-74df8f96.js",revision:"933318f8315d2da48b4e22521cdaa872"},{url:"VAADIN/build/vaadin-board-row-c70d0c55-d4708e13.js",revision:"c4bb02a3b393943b6034d5c20eee5b62"},{url:"VAADIN/build/vaadin-button-2511ad84-619d595a.js",revision:"9a96bc421312135d8bc5ee8255a75b5d"},{url:"VAADIN/build/vaadin-chart-5192dc15-ec3d6f93.js",revision:"a1837d6a7a42a35be55ba072316379e5"},{url:"VAADIN/build/vaadin-checkbox-4e68df64-411ec07e.js",revision:"9f8c900bc0d8c9153898dbcd82ec9c31"},{url:"VAADIN/build/vaadin-checkbox-group-a7c65bf2-f5f1dd85.js",revision:"987dad0e68ab350e4fc8b3cc378a0fb6"},{url:"VAADIN/build/vaadin-combo-box-96451ddd-ad73e982.js",revision:"5c088632611c3815cc838bae0b5c9b3e"},{url:"VAADIN/build/vaadin-confirm-dialog-4d718829-39ee07a2.js",revision:"d5556a63a100fa0103a5729d78f1d1ca"},{url:"VAADIN/build/vaadin-cookie-consent-46c09f8b-aadbc25e.js",revision:"a16bc7ffc9bc56eac0a9710f8313abe7"},{url:"VAADIN/build/vaadin-crud-8d161a22-d976f0cc.js",revision:"549e53342a3d8235b1254b20bbc9adb7"},{url:"VAADIN/build/vaadin-custom-field-42c85b9e-23c30e88.js",revision:"d4bb6b4c02fe42dd667dfef5887453eb"},{url:"VAADIN/build/vaadin-date-picker-f2001167-933854a4.js",revision:"573cb697f1cc73b10efa0a8509f48b47"},{url:"VAADIN/build/vaadin-date-time-picker-c8c047a7-cfc386cd.js",revision:"6a3d20ec926bc3115a51dc558aa0a645"},{url:"VAADIN/build/vaadin-details-bf336660-4790e632.js",revision:"24dcb2fa0894be4a5589484b61d3070e"},{url:"VAADIN/build/vaadin-details-summary-351a1448-0635bc04.js",revision:"e99a18cef261c6d15c0e146ea05063dd"},{url:"VAADIN/build/vaadin-dialog-53253a08-2f95d80d.js",revision:"01d6addbe61f27064bad0e8cdcd960ec"},{url:"VAADIN/build/vaadin-email-field-d7a35f04-22f57f33.js",revision:"fb2bb8cb7a6b092963b60e6abac09ca6"},{url:"VAADIN/build/vaadin-form-layout-47744b1d-c56b02c4.js",revision:"6d8fba976ce24fb9a8a87b73dc473b59"},{url:"VAADIN/build/vaadin-grid-0a4791c2-7635d912.js",revision:"857ea2ff4fad008ae59a5670fd872dd5"},{url:"VAADIN/build/vaadin-grid-pro-ff415555-169f973b.js",revision:"47137777c9bb48f84fee9ead5891c413"},{url:"VAADIN/build/vaadin-horizontal-layout-3193943f-9b84fe32.js",revision:"690ac1e02d56c5efe09df2354d1c2321"},{url:"VAADIN/build/vaadin-icon-601f36ed-2e6cd4a6.js",revision:"08e9bd99cec15a1ce964cd6761fa53bd"},{url:"VAADIN/build/vaadin-integer-field-85078932-d91c2442.js",revision:"88a9a8ff37e0a959ac1dc734f634a5e6"},{url:"VAADIN/build/vaadin-list-box-d7a8433b-305e0c17.js",revision:"7f4601bfee951219fa54257c7edb94da"},{url:"VAADIN/build/vaadin-login-form-638996c6-7260e96a.js",revision:"f59924ac43c73fc0e2af8b7cf417f40a"},{url:"VAADIN/build/vaadin-login-overlay-f8a5db8a-feeaa7b7.js",revision:"e9eead12b6b9dccfdd822bfe0b6a993b"},{url:"VAADIN/build/vaadin-map-d40a0116-8572613c.js",revision:"a3d2ae647b14f9b5819ad45d49b7ab7f"},{url:"VAADIN/build/vaadin-menu-bar-3f5ab096-69f78be2.js",revision:"cae6bc73afeef44f71db3485273bdcb7"},{url:"VAADIN/build/vaadin-message-input-996ac37c-54f7a5d3.js",revision:"65b202512cbfccf0c02b9eb11dba805a"},{url:"VAADIN/build/vaadin-message-list-70a435ba-43e8f182.js",revision:"c3ae21f302d903802b8765c546977b15"},{url:"VAADIN/build/vaadin-mobile-drag-drop-dc77d352.js",revision:"7db9aabaecd5d6ede2149ad0fe107f3c"},{url:"VAADIN/build/vaadin-multi-select-combo-box-a3373557-cc01d1c7.js",revision:"d8667e8b43cccd53b59e08bcdaff792b"},{url:"VAADIN/build/vaadin-notification-bd6eb776-a2fdbca9.js",revision:"bfc279fdc5874156cb06ba9188ca6dba"},{url:"VAADIN/build/vaadin-number-field-cb3ee8b2-b47a35ec.js",revision:"18cffc8d003d3ca6df1de5eac3ed2bee"},{url:"VAADIN/build/vaadin-password-field-d289cb18-5d08c761.js",revision:"b6a3c4e4c0659475e22dd85c7eebfbf4"},{url:"VAADIN/build/vaadin-progress-bar-309ecf1f-8430f4a7.js",revision:"7e9973b0d8ae90cb25ee63d47c3a56ad"},{url:"VAADIN/build/vaadin-radio-group-88b5afd8-874ea5e6.js",revision:"55838ca7362d228d919ec96fcd8760dd"},{url:"VAADIN/build/vaadin-rich-text-editor-8cd892f2-325bf0fd.js",revision:"13b4f4cd8482be206894604741246668"},{url:"VAADIN/build/vaadin-scroller-35e68818-b51325dd.js",revision:"e44eae86c89e295c76f55c55cb4b8198"},{url:"VAADIN/build/vaadin-select-df6e9947-9a48473e.js",revision:"4c3c2e0572d14252ffa72097d636dfc2"},{url:"VAADIN/build/vaadin-side-nav-ba80d91d-c1bc9d71.js",revision:"0233e8687395a33a755cac89c5902843"},{url:"VAADIN/build/vaadin-side-nav-item-34918f92-565dd9c4.js",revision:"28b643cd8e667be18b8f3b4cac044532"},{url:"VAADIN/build/vaadin-split-layout-80c92131-fe89e7b9.js",revision:"2e53518c36dff8408612eb924a4d8464"},{url:"VAADIN/build/vaadin-spreadsheet-59d8c5ef-36ae1d51.js",revision:"2ebedaa4dc8a67d2ebf5c1fb43af8bd1"},{url:"VAADIN/build/vaadin-tab-aaf32809-70d5d284.js",revision:"b6c734472c8dd39ff597dd266c09ca3f"},{url:"VAADIN/build/vaadin-tabs-d9a5e24e-1aafe743.js",revision:"962779797f9678a76d75209713102e90"},{url:"VAADIN/build/vaadin-tabsheet-dd99ed9a-318006f1.js",revision:"74e5ba490a168c65582e9756e09a1e35"},{url:"VAADIN/build/vaadin-text-area-83627ebc-14cd8423.js",revision:"6fbb237a9ae0e05e86d47ae491b24fcb"},{url:"VAADIN/build/vaadin-text-field-0b3db014-1751995e.js",revision:"3ba5467800e784edef5af429c57a5596"},{url:"VAADIN/build/vaadin-time-picker-715ec415-0e395b75.js",revision:"c9932e731c8ffe3f187902ac940d58fd"},{url:"VAADIN/build/vaadin-upload-d3c162ed-9467db5c.js",revision:"8beea4f87f85911095a0a8e581d4e00f"},{url:"VAADIN/build/vaadin-vertical-layout-ad4174c4-f8d8cc59.js",revision:"6606feb1a70429b58f8cd8f98af5209e"},{url:"VAADIN/build/vaadin-virtual-list-96896203-26d7a94b.js",revision:"066b1d40cee4c273731f7eb64ff1ddae"}],be=A.findIndex(s=>s.url===".")>=0;var V;(V=self.additionalManifestEntries)!=null&&V.length&&A.push(...self.additionalManifestEntries.filter(s=>s.url!=="."||!be));const pe=".",ge=new URL(self.registration.scope);async function we(s){const e=await s.text();return new Response(e.replace(/<base\s+href=[^>]*>/,`<base href="${self.registration.scope}">`),s)}function ye(s){return A.some(e=>L(e.url)===L(`${s}`))}let N=!1;function E(){return{async fetchDidFail(){N=!0},async fetchDidSucceed({response:s}){return N=!1,s}}}const me=new he({plugins:[E()]});new fe({plugins:[E()]});x(new de(async s=>{async function e(){const a=await P(pe);return a?we(a):void 0}function t(){return s.url.pathname===ge.pathname?e():ye(s.url)?P(s.request):e()}if(!self.navigator.onLine){const a=await t();if(a)return a}try{return await me.handle(s)}catch(a){const i=await t();if(i)return i;throw a}}));le(A);self.addEventListener("message",s=>{var e;typeof s.data!="object"||!("method"in s.data)||s.data.method==="Vaadin.ServiceWorker.isConnectionLost"&&"id"in s.data&&((e=s.source)==null||e.postMessage({id:s.data.id,result:N},[]))});self.addEventListener("push",s=>{var t;const e=(t=s.data)==null?void 0:t.json();e&&self.registration.showNotification(e.title,{body:e.body})});self.addEventListener("notificationclick",s=>{s.notification.close(),s.waitUntil(ve())});async function ve(){const s=new URL("/",self.location.origin).href,t=(await self.clients.matchAll({type:"window"})).find(a=>a.url===s);return t?t.focus():self.clients.openWindow(s)}