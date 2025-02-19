KC=		kotlinc -include-runtime
KFLAG=		-cp
LIBGRAPH=	libGrafoR/libGrafoR.jar
GLIB=	libGrafoR
JSIGNAL= -d



all:  GrafoR-1810245-1810649.jar



GrafoR-1810245-1810649.jar: $(GLIB)/Color.kt\
		$(GLIB)/Lado.kt\
		$(GLIB)/Arco.kt\
		$(GLIB)/ArcoCosto.kt\
		$(GLIB)/Arista.kt\
		$(GLIB)/AristaCosto.kt\
		$(GLIB)/Grafo.kt\
		$(GLIB)/GrafoDirigido.kt\
		$(GLIB)/GrafoDirigidoCosto.kt\
		$(GLIB)/GrafoNoDirigido.kt\
		$(GLIB)/GrafoNoDirigidoCosto.kt\
		$(GLIB)/DFS.kt\
		$(GLIB)/BFS.kt\
		GrafoR-1810245-1810649.kt
	$(KC) $(JSIGNAL) $@ $(GLIB)/*.kt GrafoR-1810245-1810649.kt



clean:
	rm -rf *.class META-INF 
