repl:
	clojure -A:dev

clean:
	rm -rf target out
	rm -rf resources/public/js
	rm -rf .nrepl-port
	find . -name .DS_Store -exec rm {} \;

cljfmt-check:
	clojure -R:dev -m cljfmt.main check src

cljfmt-fix:
	clojure -R:dev -m cljfmt.main fix src
